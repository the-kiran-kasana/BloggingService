package Blogging.Service.databaseLayer.services;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.stream.Collectors;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import Blogging.Service.databaseLayer.model.TagsDetails;
import Blogging.Service.databaseLayer.model.BlogDetails;
import Blogging.Service.databaseLayer.model.BlogsTagMapping;
import Blogging.Service.databaseLayer.repositories.BlogDetailsRepo;
import Blogging.Service.databaseLayer.repositories.BlogTagDetailsRepo;
import jakarta.persistence.EntityManager;

@Service
public class BlogDetailsService {
    private final BlogDetailsRepo blogDetailsRepo;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BlogTagDetailsRepo blogTagDetailsRepo;

    @Autowired
    public BlogDetailsService(BlogDetailsRepo blogDetailsRepo) {
        this.blogDetailsRepo = blogDetailsRepo;
    }

        
    @Autowired
    public void restcontroller(JdbcTemplate jdbcTemplat)
    {
        this.jdbcTemplate=jdbcTemplat;
    }

    /**
     * Save blog details.
     * @param BlogDetails
     * @return
     */
    public BlogDetails saveBlogDetails(BlogDetails blogDetails) {
        return blogDetailsRepo.save(blogDetails);
    }

    /**
     * Get the blog details by blog id.
     * @param id
     * @return
     */
    public BlogDetails getBlogDetailsById(int id) {
        System.out.println("log:[BlogDetailsService:getBlogDetailsById] : got request to get blog by id [" + id + "].");
        final BlogDetails blogDetails = blogDetailsRepo.findById(id).orElse(null);
        System.out.println("log:[BlogDetailsService:getBlogDetailsById] : Blog details for blogId [" + id + "], Details : " + blogDetails);
        return blogDetails;
    }



    /**
     * Delete blog details by id.
     * @param id
     */
    public void deleteBlogDetailsById(int id) {
        blogDetailsRepo.deleteById(id);
    }


   


    /**
     * Get blogs by tag name.
     * @param tag_name
     * @return
     */


        public List<BlogDetails> getBlogsByTagName(String tag_name) {
      
            List<BlogDetails> blogDetailslist = new ArrayList<>();

        String tagIdQuery = "SELECT tag_id FROM tag_table WHERE tag_name ="+tag_name+";";
        Integer tag_id = jdbcTemplate.queryForObject(tagIdQuery, Integer.class, tag_name);

        if (tag_id != null) {
            String childTagIdsQuery = "SELECT tag_id FROM tag_table WHERE parent_tag_id ="+tag_id+";";
            List<Integer> childTagIds = jdbcTemplate.queryForList(childTagIdsQuery, Integer.class, tag_id);

            String blogIdsQuery = "SELECT blog_id FROM blog_tag_mapping WHERE tag_id IN (:"+childTagIds+")";
            List<Integer> blogIds = jdbcTemplate.queryForList(blogIdsQuery, Integer.class, childTagIds.toArray());

            String blogsQuery = "SELECT blog_id, title, body FROM blog_table WHERE blog_id IN (:"+blogIds+")";
            jdbcTemplate.query(blogsQuery, rs -> {
                BlogDetails blogDetails = new BlogDetails();
                blogDetails.setblog_id(rs.getInt("blog_id"));
                blogDetails.setTitle(rs.getString("title"));
                blogDetails.setBody(rs.getString("body"));
                blogDetailslist.add(blogDetails);
            }, blogIds.toArray());
        }

        return blogDetailslist;
       
     }
}
        

    






