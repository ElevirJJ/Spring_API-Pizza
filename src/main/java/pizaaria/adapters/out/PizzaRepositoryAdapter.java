package pizaaria.adapters.out;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pizaaria.adapters.out.persistence.entityJpa.CustomerJpaEntity;
import pizaaria.adapters.out.persistence.entityJpa.PizzaJpaEntity;
import pizaaria.adapters.out.repository.PizzaRepository;
import pizaaria.domain.entityDominio.Customer;
import pizaaria.domain.entityDominio.Pizza;
import pizaaria.domain.port.out.PizzaRepositoryPort;

import java.util.List;
import java.util.Optional;


@Component
public class PizzaRepositoryAdapter implements PizzaRepositoryPort {

    private final PizzaRepository repository;
    private final ModelMapper modelMapper;

    public PizzaRepositoryAdapter(PizzaRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Pizza save(Pizza pizza) {
        PizzaJpaEntity pizzaJpaEntity = modelMapper.map(pizza, PizzaJpaEntity.class);
        PizzaJpaEntity pizzaSave = repository.save(pizzaJpaEntity);
        return modelMapper.map(pizzaSave, Pizza.class); /// troquei para class
    }

    @Override
    public List<Pizza> findAll() {
        return repository.findAll()
                .stream()
                .map(pizza -> modelMapper.map(pizza, Pizza.class)).toList();
    }

    @Override
    public Optional<Pizza> findById(Long id) {
        return repository.findById(id)
                .map(pizza -> modelMapper.map(pizza, Pizza.class));
    }

    @Override
    public Optional<Pizza> deleteById(Long id) {
        return repository.findById(id)
                .map(pizza -> modelMapper.map(pizza, Pizza.class));
    }
}
