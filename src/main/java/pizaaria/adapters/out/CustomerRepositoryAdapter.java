package pizaaria.adapters.out;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pizaaria.adapters.out.persistence.entityJpa.CustomerJpaEntity;
import pizaaria.adapters.out.repository.CustomerRepository;
import pizaaria.domain.entityDominio.Customer;
import pizaaria.domain.port.out.CustomerRepositoryPort;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {


    private final CustomerRepository repository;
    private final ModelMapper mapperConverter;

    public CustomerRepositoryAdapter(CustomerRepository repository, ModelMapper mapperConverter) {
        this.repository = repository;
        this.mapperConverter = mapperConverter;
    }


    @Override
    public Customer save(Customer customer) {

        CustomerJpaEntity customerEntity = mapperConverter.map(customer, CustomerJpaEntity.class);
        CustomerJpaEntity customerSave =  repository.save(customerEntity);

        return mapperConverter.map(customerSave, Customer.class);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll()
                .stream()
                .map(customerJpa -> mapperConverter.map(customerJpa, Customer.class)).toList();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id)
                .map(customerJpa-> mapperConverter.map(customerJpa, Customer.class));
    }

    @Override
    public Optional<Customer> deleteById(Long id) {
        return repository.findById(id)
                .map(customerJpa -> mapperConverter.map(customerJpa, Customer.class));
    }
}
