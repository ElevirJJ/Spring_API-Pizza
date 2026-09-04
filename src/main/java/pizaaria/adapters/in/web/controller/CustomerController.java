package pizaaria.adapters.in.web.controller;

<<<<<<< HEAD:src/main/java/pizaaria/adapters/in/web/controller/CustomerController.java
=======

>>>>>>> dc391396d247377713b0040804df70147c5a1e60:src/main/java/pizaaria/controller/CustomerController.java
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizaaria.adapters.in.web.dto.CustomerDTO;
import pizaaria.adapters.out.persistence.entityJpa.CustomerJpaEntity;
import pizaaria.application.service.CustomerService;


@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService clienteService;

    @PostMapping
    public ResponseEntity<Void> post (@RequestBody CustomerDTO clienteDTO){
        clienteService.createCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
<<<<<<< HEAD:src/main/java/pizaaria/adapters/in/web/controller/CustomerController.java
    public ResponseEntity <List<CustomerJpaEntity>> getList (){
       var getCliente =  clienteService.get();
        return ResponseEntity.ok(getCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerJpaEntity> getID (@PathVariable Long id){
=======
    @ResponseStatus(HttpStatus.OK)
    public Page<CustomerDTO> getList (Pageable pageable){
     return clienteService.get(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> getID (@PathVariable Long id){
>>>>>>> dc391396d247377713b0040804df70147c5a1e60:src/main/java/pizaaria/controller/CustomerController.java
        return ResponseEntity.ok(clienteService.buscarClienteID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody CustomerDTO clienteDTO){
        clienteService.updateCliente(id, clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteId (@PathVariable Long id){
        clienteService.deleteId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
