package pe.edu.utp.apf1.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ApiError> noEncontrado(RecursoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(LocalDateTime.now(),404,ex.getMessage(),Map.of()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validacion(MethodArgumentNotValidException ex) {
        Map<String,String> errores=new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e->errores.put(e.getField(),e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(new ApiError(LocalDateTime.now(),400,"Datos inválidos",errores));
    }
}
