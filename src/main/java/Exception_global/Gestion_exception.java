package Exception_global;

import DTO.ErrorEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Gestion_exception {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RuntimeException.class})
    public @ResponseBody @Valid ErrorEntity handleRuntimeException(RuntimeException exception){
        return new ErrorEntity(null, exception.getMessage());
    }
   @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public @Valid @ResponseBody ErrorEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        return new ErrorEntity(null, exception.getMessage());
    }



}
