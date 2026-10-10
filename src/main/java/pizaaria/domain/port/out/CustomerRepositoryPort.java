package pizaaria.domain.port.out;
import pizaaria.domain.entityDominio.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {
    Customer save (Customer customer);
    List<Customer> findAll();
    Optional<Customer> findById (Long id);
    Optional<Customer> deleteById (Long id);
}
