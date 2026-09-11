package pizaaria.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizaaria.adapters.out.persistence.entityJpa.UserJpaEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByname(String name);
}
