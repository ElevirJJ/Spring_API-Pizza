package pizaaria.domain.entityDominio;


import lombok.*;
import pizaaria.adapters.out.persistence.entityJpa.EnderecoJpaEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Customer {

    private Long id;
    private String nome;
    private String telefone;
    private Boolean ativo = true;

    private Endereco endereco;
}