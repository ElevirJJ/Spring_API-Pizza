package pizaaria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizaaria.domain.dto.PizzaDTO;
import pizaaria.domain.entity.Pizza;
import pizaaria.domain.service.PizzaService;

import java.util.List;

@RestController
@RequestMapping("/pizza")
@RequiredArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;

    @PostMapping
    public ResponseEntity<Void> Post (@RequestBody PizzaDTO pizzaDTO){
        pizzaService.createPizza(pizzaDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity <List<Pizza>> get (){
        var list = pizzaService.get();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Pizza> buscId (@PathVariable Long id){
        return ResponseEntity.ok(pizzaService.buscarPorIdPizza(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity <Void> put (@PathVariable Long id, @RequestBody PizzaDTO pizzaDTO){
        pizzaService.update(id, pizzaDTO);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete (@PathVariable Long id){
        pizzaService.deleteId(id);
        return ResponseEntity.ok().build();
    }


}
