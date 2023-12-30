package Blogging.Service.databaseLayer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="blog_table")
public class BlogDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="blog_id" , nullable = false)
    private int blog_id;

    @Column(name="title" , nullable = false)
    private String title;

    @Column(name="body" , nullable = false)
    private String body;

    public BlogDetails()
    {

    }
    
    public BlogDetails(int blog_id,String title,String body)
    {
        this.blog_id=blog_id;
        this.title=title;
        this.body=body;
    }

    public int getblog_id()
    {
        return blog_id;
    }

    public void setblog_id(int blog_id)
    {
        this.blog_id=blog_id;
    }

    public String gettitle()
    {
        return title;
    }

    public void settitle(String title)
    {
        this.title=title;
    }


    public String getbody()
    {
        return body;
    }

    public void setbody(String body)
    {
        this.body=body;
    }

    @Override
    public String toString() {
        return "{"
                + "blog_id " + blog_id +
                "title " + title +
                ",body" + body +
                "}";
    }


}
