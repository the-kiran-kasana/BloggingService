package Blogging.Service.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import Blogging.Service.Model.BlogTable;


public interface BlogRepo extends JpaRepository<BlogTable, Integer> {
    
}
