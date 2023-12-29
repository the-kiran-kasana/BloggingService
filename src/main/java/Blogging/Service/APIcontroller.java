package Blogging.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Blogging.Service.Model.BlogTable;


@RestController
public class APIcontroller {

    @Autowired
    private BusinessLogic blogService;

    @GetMapping("/byTag")
    public List<BlogTable> getBlogsByTagName(@RequestParam String tag_name) {
           List<BlogTable> blogs = blogService.getAllBlogsByTagName(tag_name);
           return blogs;
    }
      
}

