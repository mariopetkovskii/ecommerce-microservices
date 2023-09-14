package petkovskimariobachelor.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petkovskimariobachelor.userservice.entity.User;
import petkovskimariobachelor.userservice.entity.UserId;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UserId> {
    User findByEmail(String email);
    @Query("select u from User u where u.email=:email")
    Optional<User> findByEmailOptional(String email);
}
