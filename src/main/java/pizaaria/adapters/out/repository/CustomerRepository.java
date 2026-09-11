package pizaaria.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizaaria.adapters.out.persistence.entityJpa.CustomerJpaEntity;

public interface CustomerRepository extends JpaRepository<CustomerJpaEntity, Long> {

}
