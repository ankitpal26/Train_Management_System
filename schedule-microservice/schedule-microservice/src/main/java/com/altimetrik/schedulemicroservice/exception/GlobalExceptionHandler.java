package com.altimetrik.schedulemicroservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoScheduleFound.class)
    public ResponseEntity<?> handlerTrainNotFound(NoScheduleFound ex, HttpServletRequest request) {
        ApiError apiError = ApiError.builder()
                .date(new Date())
                .path(request.getRequestURI())
                .message(Arrays.asList(ex.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RouteIdNotFoundException.class)
    public ResponseEntity<?> handlerRouteIdNotExists(RouteIdNotFoundException e, HttpServletRequest httpServletRequest) {
        ApiError apiError = new ApiError(new Date(), httpServletRequest.getRequestURI(), Arrays.asList(e.getLocalizedMessage()), HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrainIdNotFoundException.class)
    public ResponseEntity<?> handlerTrainIdNotExists(TrainIdNotFoundException e, HttpServletRequest httpServletRequest) {
        ApiError apiError = new ApiError(new Date(), httpServletRequest.getRequestURI(), Arrays.asList(e.getLocalizedMessage()), HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodInvalidArgBindingException
            (MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<FieldError> dlist = ex.getBindingResult().getFieldErrors();
        List<String> listOfError = dlist.stream().map(e -> e.getField() + ":" + e.getDefaultMessage()).toList();
        ApiError apiError = new ApiError(new Date(), request.getRequestURI(), listOfError, HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
