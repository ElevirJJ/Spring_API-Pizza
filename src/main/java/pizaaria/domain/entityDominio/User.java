package pizaaria.domain.entityDominio;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    private Long id;
    private String name;
    private String password;
}
