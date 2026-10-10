package pizaaria.application.service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pizaaria.adapters.in.web.dto.CustomerDTO;
import pizaaria.domain.entityDominio.Customer;
import pizaaria.domain.exception.NotFoundException;
import pizaaria.domain.port.in.CustomerCase;
import pizaaria.domain.port.out.CustomerRepositoryPort;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerCase {

    private final CustomerRepositoryPort repositoryPort;
    private final ModelMapper modelMapper;

    public CustomerService(CustomerRepositoryPort repositoryPort, ModelMapper modelMapper) {
        this.repositoryPort = repositoryPort;
        this.modelMapper = modelMapper;
    }


    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customerSave = modelMapper.map(customerDTO, Customer.class);
        return repositoryPort.save(customerSave) ;
    }

    @Override
    public List<Customer> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public Customer getById(Long id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

    @Override
    public Optional<Customer> deleteById(Long id) {
        return repositoryPort.deleteById(id)
                .map(customer -> {
                    customer.setAtivo(false);
                   return repositoryPort.save(customer);
                });
    }

    @Override
    public Customer update(Long id, CustomerDTO customerDTO) {
        var customerEntity = getById(id);

        customerEntity.setNome(customerDTO.getNome() != null ? customerDTO.getNome() : customerEntity.getNome());
        customerEntity.setTelefone(customerDTO.getTelefone() != null ? customerDTO.getTelefone() : customerEntity.getTelefone());
        customerEntity.setEndereco(customerDTO.getEndereco() != null ? customerDTO.getEndereco() : customerEntity.getEndereco());

        return repositoryPort.save(customerEntity);
    }
}
