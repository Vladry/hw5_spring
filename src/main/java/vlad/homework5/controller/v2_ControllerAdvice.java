package vlad.homework5.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import vlad.homework5.exception.DataNotFoundException;
import vlad.homework5.exception.StoringDataException;

@Slf4j
@org.springframework.web.bind.annotation.ControllerAdvice
@RequestMapping(produces = "application/json")
public class v2_ControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = {DataNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleDataNotFoundExceptions(Throwable ex) {
        log.warn("data not found" + ex.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(value = {StoringDataException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleDataNotStoredExceptions(Throwable ex) {
        log.error("data persist error: " + ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleAllOtherExceptions(Exception ex) {
        log.error("unspecified error." + ex.getMessage());
    }


}
