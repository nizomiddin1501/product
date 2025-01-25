package zeroone.developers.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Exception thrown when a role is invalid.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductTypeException extends RuntimeException{

    public ProductTypeException(String message) {
        super(message);
    }



}
