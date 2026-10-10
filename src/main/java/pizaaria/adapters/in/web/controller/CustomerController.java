package pizaaria.adapters.in.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizaaria.adapters.in.web.dto.CustomerDTO;
import pizaaria.adapters.out.persistence.entityJpa.CustomerJpaEntity;
import pizaaria.application.service.CustomerService;
import pizaaria.domain.entityDominio.Customer;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService clienteService;

    @PostMapping
    public ResponseEntity<Void> post (@RequestBody CustomerDTO clienteDTO){
        clienteService.createCustomer(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <List<Customer>> getList() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> getID (@PathVariable Long id){
        return ResponseEntity.ok(clienteService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody CustomerDTO clienteDTO){
        clienteService.update(id, clienteDTO);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteId (@PathVariable Long id){
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
