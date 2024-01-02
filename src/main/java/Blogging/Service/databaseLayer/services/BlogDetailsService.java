package Blogging.Service.databaseLayer.services;

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


@Service
public class BlogDetailsService {

    private final BlogDetailsRepo blogDetailsRepo;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TagDetailsRepo tagDetailsRepo;


    @Autowired
    public BlogDetailsService(BlogDetailsRepo blogDetailsRepo) {
        this.blogDetailsRepo = blogDetailsRepo;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
      tagDetailsRepo.save(tagDetails);
    }



    /**
     * Get blogs by tag name.
     * 
     * @param tag_name
     * @return
     */

    public List<BlogDetails> getBlogsByTagName(String tag_name) {

        Set<Integer> hashSet = new HashSet<>();
        List<Integer> value = new ArrayList<>();
    
        String id = "SELECT tag_id FROM tag_table WHERE tag_name = ?";
        Integer tag_id = jdbcTemplate.queryForObject(id, Integer.class, tag_name);

        hashSet.add(tag_id);
        value.add(tag_id);

        do {
            List<Integer> childIds = getChildIdsForTagIds(value);
            hashSet.addAll(childIds);
            value = childIds;
        } while (!value.isEmpty());


        String blogIdFromMap = "SELECT blog_id FROM blog_tag_mapping WHERE tag_id IN (" +hashSet.stream().map(Object::toString).collect(Collectors.joining(",")) +")";
        List<Integer> blogIds = jdbcTemplate.queryForList(blogIdFromMap, Integer.class);

        String blogtabledetails = "SELECT * FROM blog_table WHERE blog_id IN (" +blogIds.stream().map(Object::toString).collect(Collectors.joining(",")) +")";
        List<BlogDetails> blogDetailsList = jdbcTemplate.query(blogtabledetails, new BeanPropertyRowMapper<>(BlogDetails.class));
        
        return blogDetailsList;

    }



    private List<Integer> getChildIdsForTagIds(List<Integer> tagIds) {

        StringJoiner placeholders = new StringJoiner(",", "(", ")");
        for (int i = 0; i < tagIds.size(); i++) {
            placeholders.add("?");
        }

        String childTagIdsQuery = "SELECT tag_id FROM tag_table WHERE parent_tag_id IN " + placeholders.toString();
        List<Integer>printallid=jdbcTemplate.queryForList(childTagIdsQuery, Integer.class, tagIds.toArray());

        System.out.println("print all childs tag is     " + printallid);
        return printallid;
    }


}
