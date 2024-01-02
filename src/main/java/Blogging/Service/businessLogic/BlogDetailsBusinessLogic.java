package Blogging.Service.businessLogic;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Blogging.Service.databaseLayer.model.BlogDetails;
import Blogging.Service.databaseLayer.model.TagsDetails;
import Blogging.Service.databaseLayer.services.BlogDetailsService;

@Service
public class BlogDetailsBusinessLogic {

    @Autowired
    private BlogDetailsService blogDetailsService;

    /*
     * This is for fetching the blog details from database.
     */
    public BlogDetails getBlogDetailsById(int blogId) {
        return blogDetailsService.getBlogDetailsById(blogId);
    }

    /*
     * This is for fetching the blog details from database by the tagName .
     */
   public List<BlogDetails> getBlogsByTag(String tag_name) {
        return blogDetailsService.getBlogsByTagName(tag_name);
    }


    /** 
     * insert the details in blog table
     */
    public void saveBlogDetails(BlogDetails blogDetails)
    {
        blogDetailsService.saveBlogDetails(blogDetails);
    }


    /** 
     * insert the details in tag table
     */
    public void saveTagDetails(TagsDetails tagDetails)
    {
       blogDetailsService.saveTagDetails(tagDetails);
    }


}
