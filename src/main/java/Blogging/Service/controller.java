package Blogging.Service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
    
    @GetMapping("/Blog_API")
    String blogpage()
    {
        return "BloggingService";
    } 
}
