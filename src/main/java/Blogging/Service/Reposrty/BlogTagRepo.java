package Blogging.Service.Reposrty;

import org.springframework.data.jpa.repository.JpaRepository;
import Blogging.Service.Model.BlogTagMapping;

public interface BlogTagRepo extends JpaRepository<BlogTagMapping, Integer> {
    
}
