package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
    User findAllById(Long id);
}
