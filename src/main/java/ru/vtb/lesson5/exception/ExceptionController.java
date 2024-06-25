package ru.vtb.lesson5.exception;

import com.fasterxml.jackson.core.JacksonException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController {
    // Обработка ошибок мапинга JSON на параметры запроса.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exc//, WebRequest request
    ) {
        return ResponseEntity.badRequest().body(
                // Сборка всех ошибок проверки в один текст.
                exc.getBindingResult().getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining ("\n"))
        );
    }

    // Обработка ошибок разбора JSON
    @ExceptionHandler(JacksonException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(JacksonException exc) {
        return ResponseEntity.badRequest().body("Ошибка разбора JSON: " + exc.getMessage());
    }

    // Обработка ошибок дублирования данных
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(DuplicateException exc) {
        return ResponseEntity.badRequest().body(exc.getMessage());
    }

    // Обработка всех прочих ошибок
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleAllException(Exception exc) {
        return ResponseEntity.internalServerError().body(exc.getMessage());
    }
}
