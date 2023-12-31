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
@Table(name="tag_table")
public class TagsDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id" , nullable = false)
    private int tag_id;

    @Column(name="tag_name" , nullable = false)
    private String tag_name;

    @Column(name="parent_tag_id" , nullable = true)
    private int parent_tag_id;


}
