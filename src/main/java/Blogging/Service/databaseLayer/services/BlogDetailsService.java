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
import Blogging.Service.databaseLayer.repositories.BlogDetailsRepo;


@Service
public class BlogDetailsService {
    private final BlogDetailsRepo blogDetailsRepo;
    private JdbcTemplate jdbcTemplate;

   


    @Autowired
    public BlogDetailsService(BlogDetailsRepo blogDetailsRepo) {
        this.blogDetailsRepo = blogDetailsRepo;
    }

    @Autowired
    public void BlogDetailsService(JdbcTemplate jdbcTemplat) {
        this.jdbcTemplate = jdbcTemplat;
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
     * Get blogs by tag name.
     * 
     * @param tag_name
     * @return
     */

    public List<BlogDetails> getBlogsByTagName(String tag_name) {

        Set<Integer> hashSet = new HashSet<>();
    

        String id = "SELECT tag_id FROM tag_table WHERE tag_name = ?";
        Integer tag_id = jdbcTemplate.queryForObject(id, Integer.class, tag_name);

        System.out.println("check the tag_id   " + tag_id);
        hashSet.add(tag_id);

        List<Integer> value = new ArrayList<>();
        value.add(tag_id);

        do {
            List<Integer> childIds = getChildIdsForTagIds(value);
            hashSet.addAll(childIds);
            value = childIds;
        } while (!value.isEmpty());

        System.out.println("show all details that present in hashset " + hashSet);





        String blogIdFromMap = "SELECT blog_id FROM blog_tag_mapping WHERE tag_id IN (" +hashSet.stream().map(Object::toString).collect(Collectors.joining(",")) +")";
        List<Integer> blogIds = jdbcTemplate.queryForList(blogIdFromMap, Integer.class);
        System.out.println("show all blog_ids that present in hashset " + blogIds);



        String blogtabledetails = "SELECT * FROM blog_table WHERE blog_id IN (" +blogIds.stream().map(Object::toString).collect(Collectors.joining(",")) +")";
        List<BlogDetails> blogDetailsList = jdbcTemplate.query(blogtabledetails, new BeanPropertyRowMapper<>(BlogDetails.class));
        System.out.println("show all blog table details that according to id blogIds " + blogDetailsList);





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
