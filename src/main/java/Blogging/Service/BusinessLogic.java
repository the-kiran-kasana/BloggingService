package Blogging.Service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Blogging.Service.Model.BlogTable;
import Blogging.Service.Model.BlogTagMapping;
import Blogging.Service.Model.TagTable;
import Blogging.Service.Repository.BlogRepo;
import Blogging.Service.Repository.BlogTagRepo;
import Blogging.Service.Repository.TagRepo;

@Service
public class BusinessLogic {

    @Autowired
    private BlogRepo blogRepository;

    @Autowired
    private TagRepo tagRepository;

    @Autowired
    private BlogTagRepo blogTagMappingRepository;

    public  List<BlogTable> getAllBlogsByTagName(String tag_name) {

            List<TagTable> tags = tagRepository.findByTagName(tag_name);
            System.out.println(" tags details" + tags);

            return blogRepository.findAll();
    }
}

