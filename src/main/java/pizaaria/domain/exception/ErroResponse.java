package pizaaria.domain.exception;

import lombok.Builder;

@Builder
public record ErroResponse(String message, Integer status) {

}
