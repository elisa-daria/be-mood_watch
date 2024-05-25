package elisa_daria.be_mood_watch.exceptions;

import elisa_daria.be_mood_watch.payloads.error.ErrorRespDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ExHandler {
    @ExceptionHandler(BadRequestEx.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorRespDTO handleBadEx(BadRequestEx ex){
        if(ex.getErrorsList()!=null){
            String msg=ex.getErrorsList().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));
            return new ErrorRespDTO(msg, LocalDateTime.now());
        }else{
            return new ErrorRespDTO(ex.getMessage(),LocalDateTime.now());
        }
    }
    @ExceptionHandler(NotFoundEx.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorRespDTO handleNotFound(NotFoundEx ex){
        return new ErrorRespDTO(ex.getMessage(),LocalDateTime.now());
    }
    @ExceptionHandler(IllegalStateException.class)
    public ErrorRespDTO handleFileToBig(IllegalStateException ex){
        ex.printStackTrace();
        return new ErrorRespDTO("file is to big.Resize!",LocalDateTime.now());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorRespDTO handleIllegalArgumentException(IllegalArgumentException ex) {
        ex.printStackTrace();
        return new ErrorRespDTO("Missing a value", LocalDateTime.now());
    }

    @ExceptionHandler(UnAuthorizedEx.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorRespDTO handleUnauthorized(UnAuthorizedEx ex){
        return new ErrorRespDTO(ex.getMessage(),LocalDateTime.now());
    }

@ExceptionHandler(AccessDeniedException.class)
@ResponseStatus(HttpStatus.FORBIDDEN)
public ErrorRespDTO handelDeniedAccess(AccessDeniedException e){
        return new ErrorRespDTO("You don't have keys to access this area",LocalDateTime.now());
}


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorRespDTO handleGenericEx(Exception ex){
        ex.printStackTrace();
        return new ErrorRespDTO("My bad, server says. Working on it!",LocalDateTime.now());
    }
}
