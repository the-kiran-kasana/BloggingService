package Blogging.Service.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import Blogging.Service.Model.TagTable;

public interface TagRepo extends JpaRepository<TagTable, Integer>{
   //  @Query("SELECT *FROM TagTable  WHERE tag_name =" + tag_name);
    List<TagTable> findByTagName(String tag_name);
}
