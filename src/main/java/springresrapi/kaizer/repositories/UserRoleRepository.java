package springresrapi.kaizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springresrapi.kaizer.domein.entities.UserRole;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,String> {
    UserRole getByAuthority(String authority);
}
