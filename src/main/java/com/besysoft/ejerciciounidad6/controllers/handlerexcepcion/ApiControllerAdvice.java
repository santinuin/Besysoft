package com.besysoft.ejerciciounidad6.controllers.handlerexcepcion;

import com.besysoft.ejerciciounidad6.dto.respose.ExceptionDTO;
import com.besysoft.ejerciciounidad6.exceptions.ObjectAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO exceptionHandler(MethodArgumentNotValidException ex){
        List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
        Map<String, String> detalle = new HashMap<>();
        errorList.forEach(e -> detalle.put(e.getField(), e.getDefaultMessage()));
        return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), "Validaciones", detalle);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO isExist(ObjectAlreadyExistException ex){
        return new ExceptionDTO(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

}
