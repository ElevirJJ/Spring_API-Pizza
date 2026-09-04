package pizaaria.domain.entityDominio;

import lombok.*;

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