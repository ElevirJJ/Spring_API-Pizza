package pizaaria.domain.port.in;

import pizaaria.adapters.in.web.dto.CustomerDTO;
import pizaaria.domain.entityDominio.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerCase {
    Customer createCustomer (CustomerDTO customerDTO);
    List<Customer> findAll();
    Customer getById (Long id);
    Optional<Customer> deleteById (Long id);
    Customer update (Long id, CustomerDTO customerDTO);

}
