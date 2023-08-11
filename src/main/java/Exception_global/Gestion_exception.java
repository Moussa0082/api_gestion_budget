package Exception_global;

import DTO.ErrorEntity;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

import java.time.format.DateTimeParseException;

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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public  @ResponseBody ErrorEntity handleCategorieNotFoundException(CategorieNotFoundException exception) {
        return new ErrorEntity(null, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DateTimeParseException.class})
    public  @ResponseBody ErrorEntity handleDateTimeParseException(DateTimeParseException exception) {
        return new ErrorEntity(null, "Format de date invalide. Utilisez le format correct.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public @ResponseBody  ErrorEntity handleConstraintViolationException(ConstraintViolationException exception) {
        return new ErrorEntity(null, "Validation échouée. Veuillez fournir des données valides.");
    }
}
