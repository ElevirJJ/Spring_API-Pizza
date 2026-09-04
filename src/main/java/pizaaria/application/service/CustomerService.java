package pizaaria.application.service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

<<<<<<< HEAD:src/main/java/pizaaria/application/service/CustomerService.java
    public List<CustomerJpaEntity> get (){
        return clienteRepository.findAll();
=======
    public Page<CustomerDTO> get (Pageable pageable){
        return clienteRepository.findAll(pageable)
                .map(c -> new CustomerDTO(c.getId(), c.getNome(), c.getTelefone(), c.getEndereco()));
>>>>>>> dc391396d247377713b0040804df70147c5a1e60:src/main/java/pizaaria/domain/service/CustomerService.java
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
