package pizaaria.domain.entityDominio;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Pizza {

    private Long id;
    private String nome;
    private String descricao;
    private Boolean ativo = true;
}