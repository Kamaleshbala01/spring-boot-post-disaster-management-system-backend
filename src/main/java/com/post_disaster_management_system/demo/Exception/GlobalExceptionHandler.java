package com.post_disaster_management_system.demo.Exception;
import com.post_disaster_management_system.demo.Model.ExceptionModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler(HttpSession httpSession) {
    }

    @ExceptionHandler(UsernameUnavailableException.class)
    public ResponseEntity<ExceptionModel> handleUsernameUnavailableException(final UsernameUnavailableException ex, final HttpServletRequest request) {
        ExceptionModel exception = new ExceptionModel(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONTINUE.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionModel> handleException(final Exception ex, final HttpServletRequest request) {
        ExceptionModel exception = new ExceptionModel(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionModel> handleUserNotFoundException(final NotFoundException ex, final HttpServletRequest request) {
        ExceptionModel exception = new ExceptionModel(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }

}
