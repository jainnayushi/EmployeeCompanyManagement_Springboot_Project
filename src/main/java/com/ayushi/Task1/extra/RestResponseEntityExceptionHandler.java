package com.ayushi.Task1.extra;

import com.ayushi.Task1.entity.ResponseMessage;
import com.ayushi.Task1.exception.CompanyNotFoundException;
import com.ayushi.Task1.exception.CustomException;
import com.ayushi.Task1.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
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


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ResponseMessage> mismatchedInputException(MethodArgumentNotValidException exception, WebRequest req) {
//        System.err.println("In MethodArgumentNotValidException");
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(Boolean.FALSE, "MethodArgumentNotValidException"));
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ResponseMessage> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
//        Map<String, String> errorMap = new HashMap<>();
//        ResponseMessage responseMessage = null;
//        System.err.println("In MethodArgumentNotValidException");
//        exception.getBindingResult().getFieldErrors().forEach(error -> {
//            String field = error.getField();
//            String message = error.getDefaultMessage();
//            errorMap.put(field, message);
//        });
//        for (Map.Entry<String, String> entry : errorMap.entrySet()) {
//            responseMessage = new ResponseMessage(Boolean.FALSE, entry.getValue());
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
//    }



}
