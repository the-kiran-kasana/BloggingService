package Blogging.Service.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Blogging.Service.businessLogic.BlogDetailsBusinessLogic;
import Blogging.Service.databaseLayer.model.BlogDetails;
import Blogging.Service.databaseLayer.model.TagsDetails;



@RestController
public class BlogDetailsRestController {

    @Autowired
    private BlogDetailsBusinessLogic blogDetailsBusinessLogic;


    @GetMapping("/blog")
    public BlogDetails getBlogById(@PathVariable("id") int blogId) {
        return blogDetailsBusinessLogic.getBlogDetailsById(blogId);
    }

    @GetMapping("/byTag")
    public List<BlogDetails> getBlogsByTag(@RequestParam String tag_name) {
        List<BlogDetails> blogs = blogDetailsBusinessLogic.getBlogsByTag(tag_name);
        return blogs;
    }


    @PostMapping("/add_blogs_details")
    public String addBlogDetails(@RequestBody BlogDetails blogDetails) {
        blogDetailsBusinessLogic.saveBlogDetails(blogDetails);
        return "Blog details added successfully";
    }



    @PostMapping("/add_tags_details")
    public String addtagsDetails(@RequestBody TagsDetails tagDetails) {
        blogDetailsBusinessLogic.saveTagDetails(tagDetails);
        return "tags details added successfully";
    }


   
}

