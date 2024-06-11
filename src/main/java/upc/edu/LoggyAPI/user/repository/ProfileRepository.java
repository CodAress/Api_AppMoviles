package upc.edu.LoggyAPI.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.LoggyAPI.user.model.Profile;
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
    boolean existsByFirstnameAndLastnameIgnoreCase(String firstname, String lastname);
    boolean existsByEmail(String email);
}
