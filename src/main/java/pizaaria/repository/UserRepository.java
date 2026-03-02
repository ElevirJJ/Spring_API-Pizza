package pizaaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizaaria.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByname(String name);
}
