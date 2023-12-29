package Blogging.Service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import Blogging.Service.Model.BlogTagMapping;

public interface BlogTagRepo extends JpaRepository<BlogTagMapping, Integer> {
     // List<BlogTagMapping> findByTagId(int tag_id);
}
