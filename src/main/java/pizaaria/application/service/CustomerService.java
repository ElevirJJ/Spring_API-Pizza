package pizaaria.application.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizaaria.adapters.in.web.dto.CustomerDTO;
import pizaaria.adapters.out.persistence.entityJpa.CustomerJpaEntity;
import pizaaria.domain.exception.NotFoundException;
import pizaaria.adapters.out.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository clienteRepository;


    public void createCliente (CustomerDTO dto){
        CustomerJpaEntity customerJpaEntity = new CustomerJpaEntity();
        customerJpaEntity.setNome(dto.nome());
        customerJpaEntity.setTelefone(dto.telefone());
        customerJpaEntity.setEnderecoJpaEntity(dto.enderecoJpaEntity());
        clienteRepository.save(customerJpaEntity);

    }

    public List<CustomerJpaEntity> get (){
        return clienteRepository.findAll();
    }

    public CustomerJpaEntity buscarClienteID (Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("cliente nao existir com o " + id));
    }


    public void deleteId(Long id){
       var customDelete = buscarClienteID(id);
       customDelete.setAtivo(false);
       clienteRepository.save(customDelete);
    }


    public void updateCliente(Long id, CustomerDTO clienteDTO) {

        CustomerJpaEntity clienteEntity = buscarClienteID(id);


        clienteEntity.setNome(clienteDTO.nome() != null ? clienteDTO.nome() : clienteEntity.getNome());
        clienteEntity.setTelefone(clienteDTO.telefone() != null ? clienteDTO.telefone() : clienteEntity.getTelefone());
        clienteEntity.setEnderecoJpaEntity(clienteDTO.enderecoJpaEntity() != null ? clienteDTO.enderecoJpaEntity() : clienteEntity.getEnderecoJpaEntity());

        clienteRepository.saveAndFlush(clienteEntity);
    }


}
