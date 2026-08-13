package pizaaria.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizaaria.domain.dto.CustomerDTO;
import pizaaria.domain.entity.Customer;
import pizaaria.domain.service.CustomerService;


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
    @ResponseStatus(HttpStatus.OK)
    public Page<CustomerDTO> getList (Pageable pageable){
     return clienteService.get(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> getID (@PathVariable Long id){
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
