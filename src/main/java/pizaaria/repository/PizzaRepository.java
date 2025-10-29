package pizaaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizaaria.domain.entity.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
