package com.mohamed.inventorymanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?>resourceNotFoundException(ResourceNotFound ex, WebRequest webRequest){
    ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
    return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?>handleMethodArgumentNotValid(MethodArgumentNotValidException ex,WebRequest webRequest){
        Map<String,List<String>>errorDetailsMap=new HashMap<>();
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        errorDetailsMap.put("errors :",errors);
        return new ResponseEntity<>(errorDetailsMap,HttpStatus.BAD_REQUEST);
    }
}
