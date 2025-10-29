package pizaaria.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizaaria.domain.dto.PizzaDTO;
import pizaaria.domain.entity.Pizza;
import pizaaria.repository.PizzaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaService {


    private final PizzaRepository pizzaRepository;

    public void createPizza (PizzaDTO dto){
        var create = new Pizza(dto.nome(), dto.descricao());
        pizzaRepository.save(create);

    }

    public List<Pizza> get (){
        return pizzaRepository.findAll();
    }

    public Pizza buscarPorIdPizza (Long id){
        return pizzaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("pizza não existir com esse " + id));
    }

    public void deleteId (Long id){
        if (pizzaRepository.existsById(id)){
            pizzaRepository.deleteById(id);
        }else {
            throw new RuntimeException(id + "não existir no banco");
        }
    }

    public void update (Long id, PizzaDTO pizzaDTO){

        Pizza pizzaEntity = pizzaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("pizza não existir"));

        pizzaEntity.setId(pizzaEntity.getId());
        pizzaEntity.setNome(pizzaDTO.nome() != null ? pizzaDTO.nome() : pizzaEntity.getNome());
        pizzaEntity.setDescricao(pizzaDTO.descricao() != null ? pizzaDTO.descricao()  : pizzaEntity.getDescricao());

        pizzaRepository.saveAndFlush(pizzaEntity);

    }
}
