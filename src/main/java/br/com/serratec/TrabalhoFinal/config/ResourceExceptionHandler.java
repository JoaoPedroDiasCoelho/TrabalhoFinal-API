package br.com.serratec.TrabalhoFinal.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.serratec.TrabalhoFinal.dto.ErroDTO;
import br.com.serratec.TrabalhoFinal.exception.DatabaseException;
import br.com.serratec.TrabalhoFinal.exception.ResourceNotFoundException;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroDTO err = new ErroDTO();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return new ResponseEntity<>(err, status);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErroDTO> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroDTO err = new ErroDTO();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Database Exception");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return new ResponseEntity<>(err, status);
    }
}
