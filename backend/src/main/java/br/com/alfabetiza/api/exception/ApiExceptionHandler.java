package br.com.alfabetiza.api.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.*;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> badRequest(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("erro",e.getMessage()));
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String,String>> notFound(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro",e.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validation(MethodArgumentNotValidException e) {
        String msg=e.getBindingResult().getFieldErrors().stream().findFirst().map(x->x.getField()+": "+x.getDefaultMessage()).orElse("Dados inválidos");
        return ResponseEntity.badRequest().body(Map.of("erro",msg));
    }
}
