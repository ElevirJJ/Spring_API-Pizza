package pizaaria.adapters.in.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizaaria.adapters.in.web.dto.CustomerDTO;
import pizaaria.adapters.out.persistence.entityJpa.CustomerJpaEntity;
import pizaaria.application.service.CustomerService;

import java.util.List;

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
    public ResponseEntity <List<CustomerJpaEntity>> getList (){
       var getCliente =  clienteService.get();
        return ResponseEntity.ok(getCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerJpaEntity> getID (@PathVariable Long id){
        return ResponseEntity.ok(clienteService.buscarClienteID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody CustomerDTO clienteDTO){
        clienteService.updateCliente(id, clienteDTO);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteId (@PathVariable Long id){
        clienteService.deleteId(id);
        return ResponseEntity.ok().build();
    }
}
