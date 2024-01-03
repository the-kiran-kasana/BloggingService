package Blogging.Service.businessLogic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import Blogging.Service.databaseLayer.model.BlogDetails;
import Blogging.Service.databaseLayer.model.TagsDetails;
import Blogging.Service.databaseLayer.repositories.BlogDetailsRepo;
import Blogging.Service.databaseLayer.repositories.TagDetailsRepo;
import lombok.NonNull;


@Service
public class BlogDetailsBusinessLogic {

    private final BlogDetailsRepo blogDetailsRepo;
    private final JdbcTemplate jdbcTemplate;
    private final TagDetailsRepo tagDetailsRepo;

    @Autowired
    public BlogDetailsBusinessLogic(
        @NonNull BlogDetailsRepo blogDetailsRepo, 
        @NonNull JdbcTemplate jdbcTemplate, 
        @NonNull TagDetailsRepo tagDetailsRepo) {
            this.blogDetailsRepo = blogDetailsRepo;
            this.jdbcTemplate = jdbcTemplate;
            this.tagDetailsRepo = tagDetailsRepo;
    }

    /**
     * Save blog details.
     * 
     * @param BlogDetails
     * @return
     */
    public BlogDetails saveBlogDetails(BlogDetails blogDetails) {
        return blogDetailsRepo.save(blogDetails);
    }

    /**
     * Get the blog details by blog id.
     * 
     * @param id
     * @return
     */
    public BlogDetails getBlogDetailsById(int id) {
        System.out.println("log:[BlogDetailsService:getBlogDetailsById] : got request to get blog by id [" + id + "].");
        final BlogDetails blogDetails = blogDetailsRepo.findById(id).orElse(null);
        System.out.println("log:[BlogDetailsService:getBlogDetailsById] : Blog details for blogId [" + id
                + "], Details : " + blogDetails);
        return blogDetails;
    }

    /**
     * Delete blog details by id.
     * 
     * @param id
     */
    public void deleteBlogDetailsById(int id) {
        blogDetailsRepo.deleteById(id);
    }



    /** 
     * insert the details in tag table
     */
    public void saveTagDetails(TagsDetails tagDetails) {
        System.out.println("log:[BlogDetailsService:saveTagDetails] : got request to save tag [" + tagDetails + "].");
        tagDetailsRepo.save(tagDetails);
        System.out.println("log:[BlogDetailsService:saveTagDetails] : Sucessfully saved tag details.");
    }

    /**
     * This is for fetching the blog details from database by the tagName .
     * 
     * @param tag_name
     * @return
     */
    public List<BlogDetails> getBlogsByTagName(String tag_name) {
        System.out.println("log:[BlogDetailsService:getBlogsByTagName] : got request to fetch blogs for tag name [" + tag_name + "].");

        Set<Integer> finalTagIds = new HashSet<>();
        List<Integer> currentTagIds = new ArrayList<>();
    
        String id = "SELECT tag_id FROM tag_table WHERE tag_name = ?";
        Integer originalTagId = jdbcTemplate.queryForObject(id, Integer.class, tag_name);

        finalTagIds.add(originalTagId);
        currentTagIds.add(originalTagId);

        do {
            List<Integer> childIds = getChildIdsForTagIds(currentTagIds);
            System.out.println("log:[BlogDetailsService:getBlogsByTagName] : Found child tags [" + childIds + "] for parent tags ["+ currentTagIds +"]");
            List<Integer> processedList = new ArrayList<>();

            childIds.forEach(ctag ->{
                if(!finalTagIds.contains(ctag)) {
                    processedList.add(ctag);
                }
            });
            
            if(childIds.size() != processedList.size()) {
                 System.out.println("log:[BlogDetailsService:getBlogsByTagName] : Found parent child tag mapping which is creating loop, So ignoring such tags.");
            }

            finalTagIds.addAll(processedList);
            // Setting the new found childerns as current tag id so that in next iteration it will search for child tags for these.
            currentTagIds = processedList;
        } while (!currentTagIds.isEmpty());

        System.out.println("log:[BlogDetailsService:getBlogsByTagName] : Found total [" + finalTagIds.size() + "] tag id for tag name [" + tag_name + "].");

        String blogIdFromMapQuery = "SELECT blog_id FROM blog_tag_mapping WHERE tag_id IN (" + finalTagIds.stream().map(Object::toString).collect(Collectors.joining(",")) + ")";
        List<Integer> blogIds = jdbcTemplate.queryForList(blogIdFromMapQuery, Integer.class);

        // Removing duplicate blogIds
        Set<Integer> uniqueBlogIds = Set.copyOf(blogIds);
        System.out.println("log:[BlogDetailsService:getBlogsByTagName] : Found total [" + uniqueBlogIds.size() + "] unique blogs for tag name [" + tag_name + "].");
        String blogtabledetails = "SELECT * FROM blog_table WHERE blog_id IN (" + uniqueBlogIds.stream().map(Object::toString).collect(Collectors.joining(",")) +")";
        List<BlogDetails> blogDetailsList = jdbcTemplate.query(blogtabledetails, (rs, rowNum) -> {
            BlogDetails blogDetails = new BlogDetails();
            blogDetails.setBlog_id(rs.getInt("blog_id"));
            blogDetails.setTitle(rs.getString("title"));
            blogDetails.setBody(rs.getString("body"));
            return blogDetails;
        });
        System.out.println("log:[BlogDetailsService:getBlogsByTagName] : Successfully returning total [" + blogDetailsList.size() + "] unique blogs for tag name [" + tag_name + "].");
        return blogDetailsList;

    }

    private List<Integer> getChildIdsForTagIds(List<Integer> tagIds) {

        StringJoiner placeholders = new StringJoiner(",", "(", ")");
        for (int i = 0; i < tagIds.size(); i++) {
            placeholders.add("?");
        }

        String childTagIdsQuery = "SELECT tag_id FROM tag_table WHERE parent_tag_id IN " + placeholders.toString();
        List<Integer>printallid=jdbcTemplate.queryForList(childTagIdsQuery, Integer.class, tagIds.toArray());

        System.out.println("log:[BlogDetailsService:getBlogsByTagName] : Found child [" + printallid.size() + "] tags for tagIds [" + tagIds + "].");
        return printallid;
    }

    /**
     * This function is for returning all the tags for a blog.
     * 
     * @param blogId
     * @return
     */
    public List<TagsDetails> getTagsByBlogId(final Integer blogId) {
        System.out.println("log:[BlogDetailsService:getTagsByBlogId] : Got blogId [" +blogId + "] in request to get all of its tag details.");
        final Set<Integer> listOfTagIdsForBlog = getAllTagIdsForBlog(blogId);
        System.out.println("log:[BlogDetailsService:getTagsByBlogId] : Found total [" + listOfTagIdsForBlog.size() + "] tag ids for blogId [" +blogId + "].");
        String tagsDetailsQuery = "SELECT * FROM tag_table WHERE tag_id IN (" + listOfTagIdsForBlog.stream().map(Object::toString).collect(Collectors.joining(",")) +")";
        final List<TagsDetails> tagDetails = jdbcTemplate.query(tagsDetailsQuery, new BeanPropertyRowMapper<>(TagsDetails.class));    
         System.out.println("log:[BlogDetailsService:getTagsByBlogId] : Returning total [" + tagDetails.size() + "] tag ids for blogId [" + blogId + "]. Tag details : " + tagDetails);
        return tagDetails;
    }

    private Set<Integer> getAllTagIdsForBlog(final Integer blogId) {
        String query = "SELECT tag_id FROM blog_tag_mapping WHERE blog_id = ?";
        List<Integer> tagIds = jdbcTemplate.queryForList(query, Integer.class, blogId);
        return Set.copyOf(tagIds);
    }

}
