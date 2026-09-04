package pizaaria.adapters.in.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizaaria.adapters.in.web.dto.PizzaDTO;
import pizaaria.adapters.out.persistence.entityJpa.PizzaJpaEntity;
import pizaaria.application.service.PizzaService;

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
<<<<<<< HEAD:src/main/java/pizaaria/adapters/in/web/controller/PizzaController.java
    public ResponseEntity <List<PizzaJpaEntity>> get (){
        var list = pizzaService.get();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity <PizzaJpaEntity> buscId (@PathVariable Long id){
=======
    @ResponseStatus(HttpStatus.OK)
    public Page<PizzaDTO> get (Pageable pageable){
        return pizzaService.get(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <Pizza> buscId (@PathVariable Long id){
>>>>>>> dc391396d247377713b0040804df70147c5a1e60:src/main/java/pizaaria/controller/PizzaController.java
        return ResponseEntity.ok(pizzaService.buscarPorIdPizza(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity <Void> put (@PathVariable Long id, @RequestBody PizzaDTO pizzaDTO){
        pizzaService.update(id, pizzaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete (@PathVariable Long id){
        pizzaService.deleteId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
