package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    //handle specific exception -AccountException
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorDetails> hadleAccountException(AccountException exception, WebRequest webRequest)
    {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false),"ACCOUNT-NOT-FOUND"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Handle Generic Exception
    public  ResponseEntity<ErrorDetails> handlerGenericException(Exception exception,WebRequest webRequest)
    {
        ErrorDetails errorDetails = new ErrorDetails(
             LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"
                );
        return  new ResponseEntity<>(errorDetails,HttpStatus
                .INTERNAL_SERVER_ERROR);
    }
}
