package Blogging.Service.databaseLayer.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import Blogging.Service.databaseLayer.model.TagsDetails;

public interface TagDetailsRepo extends JpaRepository<TagsDetails, Integer>{
   
}
