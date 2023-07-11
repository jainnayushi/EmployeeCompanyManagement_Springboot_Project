//package com.ayushi.Task1.exception;
//
//import com.ayushi.Task1.entity.ResponseMessage;
//import jakarta.validation.ConstraintViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.TransactionSystemException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@ControllerAdvice
//public class CompanyControllerAdvice {
//
//    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
//    public ResponseEntity<ResponseMessage> handleValidationException(Exception exception) {
//        List<String> errorMessages = new ArrayList<>();
//
//        if (exception instanceof ConstraintViolationException) {
//            ConstraintViolationException cve = (ConstraintViolationException) exception;
//            cve.getConstraintViolations().forEach(violation -> {
//                String field = violation.getPropertyPath().toString();
//                String message = violation.getMessage();
//                errorMessages.add(message);
//            });
//        } else if (exception instanceof MethodArgumentNotValidException) {
//            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) exception;
//            manve.getBindingResult().getFieldErrors().forEach(error -> {
//                String field = error.getField();
//                String message = error.getDefaultMessage();
//                errorMessages.add(message);
//            });
//        }
//        System.err.println(errorMessages);
//        ResponseMessage responseMessage = new ResponseMessage(Boolean.FALSE, errorMessages.toString());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
//    }
//    @ExceptionHandler(TransactionSystemException.class)
//    public ResponseEntity<ResponseMessage> handleTransactionSystemException(TransactionSystemException exception) {
//        Throwable rootCause = exception.getRootCause();
//        String errorMessage = (rootCause != null) ? rootCause.getMessage() : exception.getMessage();
//
//        ResponseMessage responseMessage = new ResponseMessage(Boolean.FALSE, errorMessage);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
//    }
//}
