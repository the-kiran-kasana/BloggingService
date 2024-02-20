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
    public BlogDetailsBusinessLogic(@NonNull BlogDetailsRepo blogDetailsRepo,  @NonNull JdbcTemplate jdbcTemplate,  @NonNull TagDetailsRepo tagDetailsRepo) {
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
        final BlogDetails blogDetails = blogDetailsRepo.findById(id).orElse(null);
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
        tagDetailsRepo.save(tagDetails);
    }





    /**
     * This is for fetching the blog details from database by the tagName .
     * 
     * @param tag_name
     * @return
     */
    public List<BlogDetails> getBlogsByTagNames(List<String> tag_names) 
    {

        Set<Integer> finalTagIds = new HashSet<>();
        List<Integer> currentTagIds = new ArrayList<>();

        /*
         * Here fetching all the tagId for provided comma seperated tags.
         */
        final List<Integer> tagIdsForTagNames = new ArrayList<>();
        String query = "SELECT tag_id FROM tag_table WHERE tag_name = ?";
        tag_names.forEach(tagName -> {
            List<Integer> tagIds = jdbcTemplate.queryForList(query, Integer.class, tagName);
            if (tagIds != null) {
                tagIdsForTagNames.addAll(tagIds);
            }
        });

        finalTagIds.addAll(tagIdsForTagNames);
        currentTagIds.addAll(tagIdsForTagNames);



        do {
            List<Integer> childIds = getChildIdsForTagIds(currentTagIds);
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




        String blogIdFromMapQuery = "SELECT blog_id FROM blog_tag_mapping WHERE tag_id IN (" + finalTagIds.stream().map(Object::toString).collect(Collectors.joining(",")) + ")";
        List<Integer> blogIds = jdbcTemplate.queryForList(blogIdFromMapQuery, Integer.class);

        // Removing duplicate blogIds
        Set<Integer> uniqueBlogIds = Set.copyOf(blogIds);
       
        String blogtabledetails = "SELECT * FROM blog_table WHERE blog_id IN (" + uniqueBlogIds.stream().map(Object::toString).collect(Collectors.joining(",")) +")";
   
        List<BlogDetails> blogDetailsList = jdbcTemplate.query(blogtabledetails, (rs, rowNum) -> {
            BlogDetails blogDetails = new BlogDetails();
            blogDetails.setBlog_id(rs.getInt("blog_id"));
            blogDetails.setTitle(rs.getString("title"));
            blogDetails.setBody(rs.getString("body"));
            return blogDetails;
        });
         return blogDetailsList;

    }







    private List<Integer> getChildIdsForTagIds(List<Integer> tagIds) {

        StringJoiner placeholders = new StringJoiner(",", "(", ")");
        for (int i = 0; i < tagIds.size(); i++) {
            placeholders.add("?");
        }

        String childTagIdsQuery = "SELECT tag_id FROM tag_table WHERE parent_tag_id IN " + placeholders.toString();
        List<Integer>printallid=jdbcTemplate.queryForList(childTagIdsQuery, Integer.class, tagIds.toArray());

         return printallid;
    }




    /**
     * This function is for returning all the tags for a blog.
     * 
     * @param blogId
     * @return
     */
    public List<TagsDetails> getTagsByBlogId(final Integer blogId) {
        final Set<Integer> listOfTagIdsForBlog = getAllTagIdsForBlog(blogId);
        String tagsDetailsQuery = "SELECT * FROM tag_table WHERE tag_id IN (" + listOfTagIdsForBlog.stream().map(Object::toString).collect(Collectors.joining(",")) +")";
        final List<TagsDetails> tagDetails = jdbcTemplate.query(tagsDetailsQuery, new BeanPropertyRowMapper<>(TagsDetails.class));    
        return tagDetails;
    }

    private Set<Integer> getAllTagIdsForBlog(final Integer blogId) {
        String query = "SELECT tag_id FROM blog_tag_mapping WHERE blog_id = ?";
        List<Integer> tagIds = jdbcTemplate.queryForList(query, Integer.class, blogId);
        return Set.copyOf(tagIds);
    }

}
