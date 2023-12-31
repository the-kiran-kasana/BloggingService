package Blogging.Service.databaseLayer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="blog_tag_mapping")
public class BlogsTagMapping {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="blog_tag_id" , nullable=false)
    private int blog_tag_id;

     @Column(name="blog_id")
     private int blog_id;

     @Column(name="tag_id")
     private int tag_id;

}
