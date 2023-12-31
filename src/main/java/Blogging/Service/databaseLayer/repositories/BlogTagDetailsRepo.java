package Blogging.Service.databaseLayer.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import Blogging.Service.databaseLayer.model.BlogsTagMapping;

public interface BlogTagDetailsRepo extends JpaRepository<BlogsTagMapping, Integer>{

    List<BlogsTagMapping> findByTagIds(String id);
}
