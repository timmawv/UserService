package avlyakulov.timur.userservice.controller;

import avlyakulov.timur.userservice.dto.ApiMessageResponse;
import avlyakulov.timur.userservice.exception.UserAgeNotValidException;
import avlyakulov.timur.userservice.exception.UserEmailAlreadyExistException;
import avlyakulov.timur.userservice.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        Map<String, List<String>> body = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            String message = error.getDefaultMessage();
            body.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
        });
        ex.getBindingResult().getGlobalErrors().forEach(objectError -> {
            String field = objectError.getObjectName();
            String message = objectError.getDefaultMessage();
            body.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
        });
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler({UserAgeNotValidException.class, UserEmailAlreadyExistException.class, UserNotFoundException.class})
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.badRequest().body(new ApiMessageResponse(e.getMessage()));
    }

//    @ExceptionHandler(DateTimeException.class)
//    public ResponseEntity<?> handleDateTime(DateTimeException e) {
//        return ResponseEntity.badRequest().body(new ApiMessageResponse("Date error. Please use pattern dd.MM.yyyy. Example: 02.02.2003 " + e.getMessage()));
//    }
}