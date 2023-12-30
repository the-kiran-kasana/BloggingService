package Blogging.Service.databaseLayer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Blogging.Service.databaseLayer.model.BlogDetails;
import Blogging.Service.databaseLayer.repositories.BlogDetailsRepo;

@Service
public class BlogDetailsService {
    private final BlogDetailsRepo blogDetailsRepo;

    @Autowired
    public BlogDetailsService(BlogDetailsRepo blogDetailsRepo) {
        this.blogDetailsRepo = blogDetailsRepo;
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
}
