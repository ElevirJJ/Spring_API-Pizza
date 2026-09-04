package pizaaria.domain.entityDominio;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Endereco {

    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
}
