package zeroone.developers.test.exceptions.handler;

import zeroone.developers.test.exceptions.ResourceNotFoundException;
import zeroone.developers.test.payload.CustomApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        CustomApiResponse apiResponse = new CustomApiResponse(
                message,
                false,
                null);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<CustomApiResponse> handleIOException(IOException ex) {
        String message = "I/O xatolik yuz berdi: " + ex.getMessage();
        CustomApiResponse apiResponse = new CustomApiResponse(
                message,
                false,
                null);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomApiResponse> handleGeneralException(Exception ex) {
        String message = "Unexpected error occurred: " + ex.getMessage();
        CustomApiResponse apiResponse = new CustomApiResponse(message, false, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
