package com.ayushi.Task1.exception;

import com.ayushi.Task1.entity.ResponseMessage;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ResponseMessage> empNotFoundException(EmployeeNotFoundException exception, WebRequest req) {
        ResponseMessage msg = new ResponseMessage(Boolean.FALSE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ResponseMessage> compNotFoundException(CompanyNotFoundException exception, WebRequest req) {
        ResponseMessage msg = new ResponseMessage(Boolean.FALSE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseMessage> customException(CustomException exception, WebRequest req) {
        ResponseMessage msg = new ResponseMessage(Boolean.FALSE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseMessage> nullPointerException (NullPointerException exception, WebRequest req) {
        ResponseMessage msg = new ResponseMessage(Boolean.FALSE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ResponseMessage> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        ResponseMessage responseMessage = null;
        System.err.println("In MethodArgumentNotValidException");
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            String message = error.getDefaultMessage();
            errorMap.put(field, message);
        });
        for (Map.Entry<String, String> entry : errorMap.entrySet()) {
            responseMessage = new ResponseMessage(Boolean.FALSE, entry.getValue());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleInvalidArgument(ConstraintViolationException exception) {
        Map<String, String> errorMap = new HashMap<>();
        ResponseMessage responseMessage = null;
        System.err.println("In ConstraintViolationException");
        exception.getConstraintViolations().forEach(violation -> {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errorMap.put(field, message);
        });
        for (Map.Entry<String, String> entry : errorMap.entrySet()) {
            responseMessage = new ResponseMessage(Boolean.FALSE, entry.getValue());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
    }

    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<ResponseMessage> mismatchedInputException(MismatchedInputException exception, WebRequest req) {
        System.err.println("In MismatchedInputException.class");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(Boolean.FALSE, "Employee data need to be passed properly"));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ResponseMessage> handleMissingServletRequestPartException(MissingServletRequestPartException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(Boolean.FALSE, "Image is not properly set"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(Boolean.FALSE, "Request body need to be passed properly"));
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseMessage> handleHttpMessageNotReadableException(Exception exception) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(Boolean.FALSE, "Something went wrong!"));
//    }
}