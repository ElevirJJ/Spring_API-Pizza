package pizaaria.adapters.in.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizaaria.adapters.in.web.dto.PizzaDTO;
import pizaaria.adapters.out.persistence.entityJpa.PizzaJpaEntity;
import pizaaria.application.service.PizzaService;
import pizaaria.domain.entityDominio.Pizza;

import java.util.List;

@RestController
@RequestMapping("/pizza")
@RequiredArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;

    @PostMapping
    public ResponseEntity<Void> Post (@RequestBody PizzaDTO pizzaDTO){
        pizzaService.createPizza(pizzaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <List<Pizza>> get (){
        return ResponseEntity.ok(pizzaService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <Pizza> buscaId (@PathVariable Long id){
        return ResponseEntity.ok(pizzaService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity <Void> put (@PathVariable Long id, @RequestBody PizzaDTO pizzaDTO){
        pizzaService.update(id, pizzaDTO);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete (@PathVariable Long id){
        pizzaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
