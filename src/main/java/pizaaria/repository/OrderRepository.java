package pizaaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizaaria.domain.entity.Customer;
import pizaaria.domain.entity.Order;
import pizaaria.domain.entity.Pizza;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
