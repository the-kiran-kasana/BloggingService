package Blogging.Service.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tag_table")
public class TagTable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id" , nullable = false)
    private int tag_id;

    @Column(name="tag_name" , nullable = false)
    private String tag_name;

    @Column(name="parent_tag_id" , nullable = true)
    private int parent_tag_id;

    public TagTable()
    {

    }

    public TagTable(int tag_id,String tag_name,int parent_tag_id)
    {
        this.tag_id=tag_id;
        this.tag_name=tag_name;
        this.parent_tag_id=parent_tag_id;
    }

    public int gettag_id()
    {
        return tag_id;
    }

    public void settag_id(int tag_id)
    {
        this.tag_id=tag_id;
    }
 
    public String gettag_name()
    {
        return tag_name;
    }

    public void settag_name(String tag_name)
    {
        this.tag_name=tag_name;
    }

    public int getparent_tag_id()
    {
        return parent_tag_id;
    }

    public void setparent_tag_id(int parent_tag_id)
    {
        this.parent_tag_id=parent_tag_id;
    }



    @Override
    public String toString() {
        return "{"
                + "tag_id " + tag_id +
                "tag_name " + tag_name +
                ",parent_tag_id" + parent_tag_id +
                "}";
    }

}
