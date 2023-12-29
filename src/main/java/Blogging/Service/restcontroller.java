// package Blogging.Service;

// import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;
// import Blogging.Service.Model.BlogTable;
// import Blogging.Service.Model.BlogTagMapping;
// import Blogging.Service.Model.TagTable;
// import Blogging.Service.Repository.BlogRepo;
// import Blogging.Service.Repository.BlogTagRepo;
// import Blogging.Service.Repository.TagRepo;
// import java.util.List;


// @Service
// @RestController
// public class restcontroller{



//     @Autowired
//     private BlogRepo blog_repo;

//     @Autowired
//     private TagRepo tag_repo;

//     @Autowired
//     private BlogTagRepo blog_tag_repo;

//     // private final JdbcTemplate jdbcTemplate;

//     // public restcontroller(JdbcTemplate jdbcTemplate) {
//     //     this.jdbcTemplate = jdbcTemplate;
//     // }

//     @GetMapping("/BlogTableDetails")
//     public List<BlogTable> GetAllDetailOfBlog() {
//         return blog_repo.findAll();
//     }

//     @GetMapping("/TagTableDetails")
//     public List<TagTable> GetAllDetailOfTag() {
//         return tag_repo.findAll();
//     }

//     @GetMapping("/Blog_TagTableDetails")
//     public List<BlogTagMapping> GetAllDetailOfBlog_Tag() {
//         return blog_tag_repo.findAll();
//     }
    
    

// }
