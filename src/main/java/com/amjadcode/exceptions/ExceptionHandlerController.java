package com.amjadcode.exceptions;

import com.amjadcode.commons.ApiResponse;
import org.slf4j.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.*;
import org.springframework.security.authentication.*;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.mvc.method.annotation.*;

import javax.persistence.*;
import javax.servlet.http.*;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.*;
import java.util.stream.*;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(final ConstraintViolationException exception, final HttpServletRequest request) {

        ArrayList<ConstraintViolation<?>> validation = new ArrayList<>(exception.getConstraintViolations());

        Map<String, String> errors = new HashMap<>();

        for (ConstraintViolation<?> constraintViolation : validation) {
            errors.put("error", constraintViolation.getMessage());
        }

        return new ResponseEntity<>(new ApiResponse(false, "400" , "Bad Request!" ,"Check the returned errors!" , errors), HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(AccountExpiredException.class)
    public ResponseEntity<?> accountExpiredException(final AccountExpiredException ex, final HttpServletRequest request) {

        LOGGER.warn("AccountExpiredException handled.  Message: "+ Arrays.toString(ex.getStackTrace()) +" Requester-IP: "+request.getRemoteAddr()+". URL: "+request.getRequestURI()+ " Method: "+request.getMethod());
        return new ResponseEntity<>(new ApiResponse(false, "401", "User account has expired",
                ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({DisabledException.class})
    public ResponseEntity<?> disabledException(final DisabledException ex, final HttpServletRequest request) {

        LOGGER.warn("DisabledException handled.  Message: "+ Arrays.toString(ex.getStackTrace()) +" Requester-IP: "+request.getRemoteAddr()+". URL: "+request.getRequestURI()+ " Method: "+request.getMethod());
        return new ResponseEntity<>(new ApiResponse(false, "401", "User disabled",
                ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> badCredentialsException(final BadCredentialsException ex, final HttpServletRequest request) {

        LOGGER.warn("BadCredentialsException handled.  Message: "+ Arrays.toString(ex.getStackTrace()) +" Requester-IP: "+request.getRemoteAddr()+". URL: "+request.getRequestURI()+ " Method: "+request.getMethod());
        return new ResponseEntity<>(new ApiResponse(false, "401", "Invalid credentials",
                ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<?> credentialsExpiredException(final CredentialsExpiredException ex, final HttpServletRequest request) {

        LOGGER.warn("CredentialsExpiredException handled.  Message: "+ Arrays.toString(ex.getStackTrace()) +" Requester-IP: "+request.getRemoteAddr()+". URL: "+request.getRequestURI()+ " Method: "+request.getMethod());
        return new ResponseEntity<>(new ApiResponse(false, "401", " User credentials have expired",
                ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentException(final IllegalArgumentException ex, final HttpServletRequest request) {

        LOGGER.error("IllegalArgumentException handled.  Message: "+ Arrays.toString(ex.getStackTrace()) +" Requester-IP: "+request.getRemoteAddr()+". URL: "+request.getRequestURI()+ " Method: "+request.getMethod());
        return new ResponseEntity<>(new ApiResponse(false, "400", ex.getMessage(), "Data Conflict."
                , request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> illegalStateException(final IllegalStateException ex, final HttpServletRequest request) {

        LOGGER.error("MethodArgumentTypeMismatchException handled.  Message: "+ Arrays.toString(ex.getStackTrace()) +" Requester-IP: "+request.getRemoteAddr()+". URL: "+request.getRequestURI()+ " Method: "+request.getMethod());
        return new ResponseEntity<>(new ApiResponse(false, "400", ex.getMessage(), "Data Conflict."
                , request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException(final AccessDeniedException ex, final HttpServletRequest request) {

        LOGGER.warn("AccessDeniedException handled.  Message: "+ ex.getMessage() +" Requester-IP: "+request.getRemoteAddr()+". URL: "+request.getRequestURI()+ " Method: "+request.getMethod());
        return new ResponseEntity<>(new ApiResponse(false, "401", ex.getMessage(), "unauthorized!"
                , request.getRequestURI()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NonUniqueResultException.class)
    public ResponseEntity<?> nonUniqueResultException(final NonUniqueResultException ex, final HttpServletRequest request) {

        LOGGER.error("NullPointerException handled. Message: "+ ex.getMessage() +" Requester-IP: "+request.getRemoteAddr()+". URL: "+request.getRequestURI()+ " Method: "+request.getMethod());
        return new ResponseEntity<>(new ApiResponse(false, "500", ex.getMessage(), "internal server error!"
                , request.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> IOException(final IOException ex, final HttpServletRequest request) {

        LOGGER.error("IOException handled. Message: "+ ex.getMessage() +" Requester-IP: "+request.getRemoteAddr()+". URL: "+request.getRequestURI()+ " Method: "+request.getMethod());
        return new ResponseEntity<>(new ApiResponse(false, "500", ex.getMessage(), "internal server error!"
                , request.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> fileNotFoundException(final FileNotFoundException ex, final HttpServletRequest request) {

        LOGGER.warn("FileNotFoundException handled. Message: "+ ex.getMessage() +" Requester-IP: "+request.getRemoteAddr()+". URL: "+request.getRequestURI()+ " Method: "+request.getMethod());
        return new ResponseEntity<>(new ApiResponse(false, "400", ex.getMessage(), ""
                , request.getRequestURI()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundException(EntityNotFoundException ex, final HttpServletRequest request) throws Exception {
        LOGGER.error("EntityNotFoundException handled. Message: "+ ex.getMessage() +" Requester-IP: "+request.getRemoteAddr()+". URL: "+request.getRequestURI()+ " Method: "+request.getMethod());
        ResponseEntity.BodyBuilder builder;
        ApiResponse apiResponse;
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            builder = ResponseEntity.status(responseStatus.value());
            apiResponse = new ApiResponse(false, responseStatus.code() + "", "error." + responseStatus.value().value(), responseStatus.reason());
        } else {
            builder = ResponseEntity.status(HttpStatus.BAD_REQUEST);
            apiResponse = new ApiResponse(false, "400" , ex.getMessage(), "Internal server error");
        }
        return builder.body(apiResponse);
    }


    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                           @NotNull HttpHeaders headers, @NotNull HttpStatus status, @NotNull WebRequest request) {

        List<String> validationListKey = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getField).toList();
        List<String> validationListValue = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        Map<String, String> errors = new HashMap<>();

        for (int i = 0; i < validationListKey.size(); i++){
            errors.put(validationListKey.get(i), validationListValue.get(i));
        }


        return new ResponseEntity<>(new ApiResponse(false, "400", "Validation error", "", errors), HttpStatus.BAD_REQUEST);
    }


}