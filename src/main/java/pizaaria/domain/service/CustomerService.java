package pizaaria.domain.service;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizaaria.domain.dto.CustomerDTO;
import pizaaria.domain.entity.Customer;
import pizaaria.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository clienteRepository;


    public void createCliente (CustomerDTO dto){
        Customer customer = new Customer();
        customer.setNome(dto.nome());
        customer.setTelefone(dto.telefone());
        customer.setEndereco(dto.endereco());
        clienteRepository.save(customer);
    }

    public List<Customer> get (){
        return clienteRepository.findAll();
    }

    public Customer buscarClienteID (Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("cliente não existir com o " + id));

    }

    public void deleteId(Long id){
        if (clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("cliente com " + id + " não existir");
        }
    }


    public void updateCliente(Long id, CustomerDTO clienteDTO) {

        Customer clienteEntity = buscarClienteID(id);


        clienteEntity.setNome(clienteDTO.nome() != null ? clienteDTO.nome() : clienteEntity.getNome());
        clienteEntity.setTelefone(clienteDTO.telefone() != null ? clienteDTO.telefone() : clienteEntity.getTelefone());
        clienteEntity.setEndereco(clienteDTO.endereco() != null ? clienteDTO.endereco() : clienteEntity.getEndereco());

        clienteRepository.saveAndFlush(clienteEntity);
    }


}
