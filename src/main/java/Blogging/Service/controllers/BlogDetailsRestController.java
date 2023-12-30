package Blogging.Service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Blogging.Service.businessLogic.BlogDetailsBusinessLogic;
import Blogging.Service.databaseLayer.model.BlogDetails;

@RestController
public class BlogDetailsRestController {

    @Autowired
    private BlogDetailsBusinessLogic blogDetailsBusinessLogic;

    @GetMapping("/blog/{id}")
    public BlogDetails getBlogById(@PathVariable("id") int blogId) {
        System.out.println("log:[BlogDetailsRestController:getBlogById] : got request to get blog by id [" + blogId + "].");
        return blogDetailsBusinessLogic.getBlogDetailsById(blogId);
    }
      
}

