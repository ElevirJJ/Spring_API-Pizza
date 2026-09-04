package pizaaria.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizaaria.adapters.in.web.dto.PizzaDTO;
import pizaaria.adapters.out.persistence.entityJpa.PizzaJpaEntity;
import pizaaria.domain.exception.NotFoundException;
import pizaaria.adapters.out.repository.PizzaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaService {


    private final PizzaRepository pizzaRepository;

    public void createPizza (PizzaDTO dto){
        PizzaJpaEntity create = new PizzaJpaEntity();
        create.setNome(dto.nome());
        create.setDescricao(dto.descricao());
        pizzaRepository.save(create);

    }

    public List<PizzaJpaEntity> get (){
        return pizzaRepository.findAll();
    }

    public PizzaJpaEntity buscarPorIdPizza (Long id){
        return pizzaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("pizza nao existir com esse " + id));
    }

    public void deleteId (Long id){
        var pizzaDelete = buscarPorIdPizza(id);
        pizzaDelete.setAtivo(false);
        pizzaRepository.save(pizzaDelete);
    }

    public void update (Long id, PizzaDTO pizzaDTO){

        PizzaJpaEntity pizzaJpaEntityEntity = buscarPorIdPizza(id);


        pizzaJpaEntityEntity.setId(pizzaJpaEntityEntity.getId());
        pizzaJpaEntityEntity.setNome(pizzaDTO.nome() != null ? pizzaDTO.nome() : pizzaJpaEntityEntity.getNome());
        pizzaJpaEntityEntity.setDescricao(pizzaDTO.descricao() != null ? pizzaDTO.descricao()  : pizzaJpaEntityEntity.getDescricao());

        pizzaRepository.saveAndFlush(pizzaJpaEntityEntity);

    }
}
