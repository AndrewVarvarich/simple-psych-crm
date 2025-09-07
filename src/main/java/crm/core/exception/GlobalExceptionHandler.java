package crm.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFoundException(ClientNotFoundException e) {
        log.error("Ошибка поиска: {}", e.getMessage());
        return Map.of(
                "error", "Ошибка поиска",
                "description", e.getMessage()
        );
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(final ValidationException e) {
        log.error("Ошибка валидации данных: {}", e.getMessage());
        return Map.of(
                "error", "Данные введены некорректно",
                "description", e.getMessage()
        );
    }
}
