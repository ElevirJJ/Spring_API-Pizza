package pizaaria.adapters.in.web.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pizaaria.domain.exception.ErroResponse;
import pizaaria.domain.exception.InternalServerError;
import pizaaria.domain.exception.NotFoundException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErroResponse> erro (NotFoundException not){
        ErroResponse erroResponse = ErroResponse.builder()
                .message(not.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResponse);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<ErroResponse> internal (InternalServerError serverError){
        ErroResponse erroResponse = ErroResponse.builder()
                .message(serverError.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
    }
}
