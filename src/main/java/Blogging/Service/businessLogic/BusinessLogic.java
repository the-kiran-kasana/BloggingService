package Blogging.Service.businessLogic;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Blogging.Service.databaseLayer.model.BlogDetails;
import Blogging.Service.databaseLayer.model.BlogsTagMapping;
import Blogging.Service.databaseLayer.model.TagsDetails;
import Blogging.Service.databaseLayer.repositories.BlogDetailsRepo;
//import Blogging.Service.databaseLayer.repositories.BlogTagRepo;
//import Blogging.Service.databaseLayer.repositories.TagRepo;

@Service
public class BusinessLogic {

    /*
    @Autowired
    private BlogDetailsRepo blogRepository;

    @Autowired
    private TagRepo tagRepository;

    @Autowired
    private BlogTagRepo blogTagMappingRepository;

    public  List<BlogDetails> getAllBlogsByTagName(String tag_name) {

            List<TagsDetails> tags = tagRepository.findByTagName(tag_name);
            System.out.println(" tags details" + tags);

            return blogRepository.findAll();
    }
    */
}

