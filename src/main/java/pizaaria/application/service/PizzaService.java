package pizaaria.application.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pizaaria.adapters.in.web.dto.PizzaDTO;
import pizaaria.adapters.out.persistence.entityJpa.PizzaJpaEntity;
import pizaaria.domain.entityDominio.Pizza;
import pizaaria.domain.exception.NotFoundException;
import pizaaria.adapters.out.repository.PizzaRepository;
import pizaaria.domain.port.in.PizzaCase;
import pizaaria.domain.port.out.PizzaRepositoryPort;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService implements PizzaCase {


    private final PizzaRepositoryPort pizzaRepositoryPort;
    private final ModelMapper modelMapper;

    public PizzaService(PizzaRepositoryPort pizzaRepositoryPort, ModelMapper modelMapper) {
        this.pizzaRepositoryPort = pizzaRepositoryPort;
        this.modelMapper = modelMapper;
    }


    @Override
    public Pizza createPizza(PizzaDTO pizzaDTO) {
        Pizza pizza = modelMapper.map(pizzaDTO, Pizza.class);

        return pizzaRepositoryPort.save(pizza);
    }

    @Override
    public List<Pizza> findAll() {
        return pizzaRepositoryPort.findAll();
    }

    @Override
    public Pizza getById(Long id) {
        return pizzaRepositoryPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

    @Override
    public Optional<Pizza> deleteById(Long id) {
        return pizzaRepositoryPort.deleteById(id)
                .map(pizza -> {
                    pizza.setAtivo(false);
                   return pizzaRepositoryPort.save(pizza);
                });
    }


    @Override
    public Pizza update (Long id, PizzaDTO pizzaDTO) {

        var pizzaJpaEntityEntity = getById(id);


        pizzaJpaEntityEntity.setId(pizzaJpaEntityEntity.getId());
        pizzaJpaEntityEntity.setNome(pizzaDTO.getNome() != null ? pizzaDTO.getNome() : pizzaJpaEntityEntity.getNome());
        pizzaJpaEntityEntity.setDescricao(pizzaDTO.getDescricao() != null ? pizzaDTO.getDescricao() : pizzaJpaEntityEntity.getDescricao());



        return pizzaRepositoryPort.save(pizzaJpaEntityEntity);
    }
}
