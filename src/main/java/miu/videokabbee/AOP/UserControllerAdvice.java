package miu.videokabbee.AOP;


//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.ConstraintViolationException;
import jakarta.servlet.ServletException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import miu.videokabbee.ExceptionHandling.ExceptionHandling;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;
//extends ResponseEntityExceptionHandler
@RestControllerAdvice
public class UserControllerAdvice {

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
      List<String> errorMessages =ex.getBindingResult().getAllErrors().stream()
              .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

      ExceptionHandling response=new ExceptionHandling(errorMessages);

       return ResponseEntity.badRequest().body(response.toString());

   }

   @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
   public ResponseEntity<String> handleAccessToUnauthorized(){

      return ResponseEntity.badRequest().body("You have to log in to have  access");

         }
}
