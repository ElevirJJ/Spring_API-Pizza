package pizaaria.domain.port.in;

import pizaaria.adapters.in.web.dto.PizzaDTO;
import pizaaria.domain.entityDominio.Pizza;

import java.util.List;
import java.util.Optional;

public interface PizzaCase {
    Pizza createPizza(PizzaDTO pizzaDTO);
    List<Pizza> findAll ();
    Pizza getById (Long id);
    Optional<Pizza> deleteById (Long id);
    Pizza update (Long id, PizzaDTO pizzaDTO);

}
