package Blogging.Service.businessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Blogging.Service.databaseLayer.model.BlogDetails;
import Blogging.Service.databaseLayer.services.BlogDetailsService;

@Service
public class BlogDetailsBusinessLogic {

    @Autowired
    private BlogDetailsService blogDetailsService;

    /*
     * This is for fetching the blog details from database.
     */
    public BlogDetails getBlogDetailsById(int blogId) {
        System.out.println("log:[BlogDetailsBusinessLogic:getBlogDetailsById] : got request to get blog by id [" + blogId + "].");
        return blogDetailsService.getBlogDetailsById(blogId);
    }
}
