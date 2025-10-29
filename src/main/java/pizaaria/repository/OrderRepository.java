package pizaaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizaaria.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
