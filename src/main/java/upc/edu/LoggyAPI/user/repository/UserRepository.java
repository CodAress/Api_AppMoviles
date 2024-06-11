package upc.edu.LoggyAPI.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.LoggyAPI.user.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameIgnoreCase(String userName);
    boolean existsByUsernameAndPassword(String email, String password);
}
