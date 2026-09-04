package pizaaria.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizaaria.adapters.out.persistence.entityJpa.OrderJpaEntity;

public interface OrderRepository extends JpaRepository<OrderJpaEntity,Long> {

}
