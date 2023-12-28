package Blogging.Service.Reposrty;

import org.springframework.data.jpa.repository.JpaRepository;
import Blogging.Service.Model.TagTable;

public interface TagRepo extends JpaRepository<TagTable, Integer>{
    
}
