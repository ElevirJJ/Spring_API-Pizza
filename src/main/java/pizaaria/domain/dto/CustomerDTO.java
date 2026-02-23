package pizaaria.domain.dto;

import pizaaria.domain.entity.Customer;
import pizaaria.domain.entity.Endereco;

public record CustomerDTO(Long id, String nome, String telefone, Endereco endereco) {



}
