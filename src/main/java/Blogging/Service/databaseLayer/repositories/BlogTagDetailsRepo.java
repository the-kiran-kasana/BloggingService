package Blogging.Service.databaseLayer.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import Blogging.Service.databaseLayer.model.BlogsTagMapping;

public interface BlogTagDetailsRepo extends JpaRepository<BlogsTagMapping, Integer>{
    

}
