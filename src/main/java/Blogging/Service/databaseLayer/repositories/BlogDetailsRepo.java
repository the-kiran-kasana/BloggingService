package Blogging.Service.databaseLayer.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import Blogging.Service.databaseLayer.model.BlogDetails;


public interface BlogDetailsRepo extends JpaRepository<BlogDetails, Integer> {
    
}
