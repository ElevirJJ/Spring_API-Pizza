package pizaaria.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizaaria.domain.dto.CustomerDTO;
import pizaaria.domain.entity.Customer;
import pizaaria.domain.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService clienteService;

    @PostMapping
    public ResponseEntity<Void> post (@RequestBody @Valid CustomerDTO clienteDTO){
        clienteService.createCliente(clienteDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity <List<Customer>> getList (){
       var getCliente =  clienteService.get();
        return ResponseEntity.ok(getCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getID (@PathVariable Long id){
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
