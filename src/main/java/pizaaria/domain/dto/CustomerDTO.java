package pizaaria.domain.dto;

import pizaaria.domain.entity.Customer;
import pizaaria.domain.entity.Endereco;

public record CustomerDTO(Long id, String nome, String telefone, Endereco endereco) {
    public CustomerDTO(Customer cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEndereco());
    }



}
