package pizaaria.domain.port.out;

import pizaaria.domain.entityDominio.Pizza;

import java.util.List;
import java.util.Optional;

public interface PizzaRepositoryPort {
    Pizza save (Pizza pizza);
    List<Pizza> findAll ();
    Optional<Pizza> findById (Long id);
    Optional<Pizza> deleteById (Long id);

}
