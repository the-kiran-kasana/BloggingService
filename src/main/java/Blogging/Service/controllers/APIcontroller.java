package Blogging.Service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Blogging.Service.businessLogic.BusinessLogic;
import Blogging.Service.databaseLayer.model.BlogDetails;


@RestController
public class APIcontroller {

    @Autowired
    private BusinessLogic blogService;

    @GetMapping("/byTag")
    public List<BlogDetails> getBlogsByTagName(@RequestParam String tag_name) {
           //List<BlogDetails> blogs = blogService.getAllBlogsByTagName(tag_name);
           //return blogs;
           return null;
    }
      
}

