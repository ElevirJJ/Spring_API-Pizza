package pizaaria.adapters.in.web.dto;

import pizaaria.adapters.out.persistence.entityJpa.EnderecoJpaEntity;

public record CustomerDTO(Long id, String nome, String telefone, EnderecoJpaEntity enderecoJpaEntity) {



}
