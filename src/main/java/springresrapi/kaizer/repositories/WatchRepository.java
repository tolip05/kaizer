package springresrapi.kaizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springresrapi.kaizer.domein.entities.Watch;

import java.util.List;

@Repository
public interface WatchRepository extends JpaRepository<Watch,String> {
    List<Watch> findTop4ByOrderByViewsDesc();
}
