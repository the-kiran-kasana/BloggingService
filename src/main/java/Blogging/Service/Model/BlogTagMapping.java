package Blogging.Service.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="blog_tag_mapping")
public class BlogTagMapping {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="blog_tag_id" , nullable=false)
    private int blog_tag_id;

     @Column(name="blog_id")
     private int blog_id;

     @Column(name="tag_id")
     private int tag_id;


     public BlogTagMapping()
     {

     }

     public BlogTagMapping(int blog_tag_id,int blog_id,int tag_id)
     {
        this.blog_tag_id=blog_tag_id;
        this.blog_id=blog_id;
        this.tag_id=tag_id;
     }


     public int getblog_tag_id()
     {
         return blog_tag_id;
     }
 
     public void setblog_tag_id(int blog_tag_id)
     {
         this.blog_tag_id=blog_tag_id;
     }
 
     public int gettag_id()
     {
         return tag_id;
     }
 
     public void settag_id(int tag_id)
     {
         this.tag_id=tag_id;
     }

     public int getblog_id()
     {
         return blog_id;
     }
 
     public void setblog_id(int blog_id)
     {
         this.blog_id=blog_id;
     }

     @Override
     public String toString() {
         return "{"
                  +"blog_tag_id " + blog_tag_id +
                  "tag_id " + tag_id +
                  ",blog_id " + blog_id +
                 "}";
     }
 

}
