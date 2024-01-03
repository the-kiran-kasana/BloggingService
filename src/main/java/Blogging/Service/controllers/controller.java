package Blogging.Service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
    
    /**
     * This controller is for returning page the for searching blogs using tag names.
     * @return
     */
    @GetMapping("/blogs")
    String blogpage()
    {
        return "bloggingPage";
    } 

    /**]
     * This controller is for returning page for inserting blogs.
     */
    /*
    @GetMapping("/blogs/add")
    String AddBlogpage()
    {
        return "insertBlogs";
    } 
    */
}
