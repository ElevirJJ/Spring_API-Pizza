package pizaaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizaaria.domain.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
