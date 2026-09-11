package pizaaria.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizaaria.adapters.out.persistence.entityJpa.PizzaJpaEntity;

public interface PizzaRepository extends JpaRepository<PizzaJpaEntity, Long> {
}
