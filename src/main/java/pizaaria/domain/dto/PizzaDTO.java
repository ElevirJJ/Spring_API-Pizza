package pizaaria.domain.dto;

import pizaaria.domain.entity.Pizza;

public record PizzaDTO(Long id , String nome, String descricao) {
    public PizzaDTO (Pizza pizza){
        this(pizza.getId(), pizza.getNome(), pizza.getDescricao());
    }
}
