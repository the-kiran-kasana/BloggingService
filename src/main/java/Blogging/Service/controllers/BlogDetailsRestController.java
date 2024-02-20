package Blogging.Service.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Blogging.Service.Models.BlogDetailsResponse;
import Blogging.Service.businessLogic.BlogDetailsBusinessLogic;
import Blogging.Service.databaseLayer.model.BlogDetails;
import Blogging.Service.databaseLayer.model.TagsDetails;

@RestController
public class BlogDetailsRestController {

    @Autowired
    private BlogDetailsBusinessLogic blogDetailsBusinessLogic;

    

    @GetMapping("/blogs/tag")
    public List<BlogDetailsResponse> getBlogsByTag(@RequestParam String tag_name) {
        List<String> tagNames  = Arrays.asList(tag_name.split(","));
        List<BlogDetails> blogs = blogDetailsBusinessLogic.getBlogsByTagNames(tagNames);
        System.out.println(blogs);
        final List<BlogDetailsResponse> blogDetailsFinalResponse = new ArrayList<>();
        blogs.forEach(blog -> {
            System.out.println(blog);
            final List<TagsDetails> blogTagsDetails = blogDetailsBusinessLogic.getTagsByBlogId(blog.getBlog_id());
            final BlogDetailsResponse blogDetailsResponse = 
                    BlogDetailsResponse.builder()
                    .blog_id(blog.getBlog_id())
                    .title(blog.getTitle())
                    .body(blog.getBody())
                    .blogTags(blogTagsDetails)
                    .build();
            blogDetailsFinalResponse.add(blogDetailsResponse);
        });
        return blogDetailsFinalResponse;
    }




}

