package pizaaria.domain.entity;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Endereco {
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    @NotBlank(message = "O nome é obrigatório")
    private String cep;
}
