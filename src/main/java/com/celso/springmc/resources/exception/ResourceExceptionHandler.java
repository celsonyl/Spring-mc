package com.celso.springmc.resources.exception;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.celso.springmc.services.exceptions.AuthorizationException;
import com.celso.springmc.services.exceptions.DataIntegrityException;
import com.celso.springmc.services.exceptions.FileException;
import com.celso.springmc.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException error, HttpServletRequest request) {
        StandardError erro = new StandardError(HttpStatus.NOT_FOUND.value(), error.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataViolation(DataIntegrityException error, HttpServletRequest request) {
        StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), error.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodValidation(MethodArgumentNotValidException error, HttpServletRequest request) {
        ValidationError erro = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());

        for (FieldError x : error.getBindingResult().getFieldErrors()) {
            erro.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorization(AuthorizationException error, HttpServletRequest request) {
        StandardError erro = new StandardError(HttpStatus.FORBIDDEN.value(), error.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<StandardError> fileException(FileException error, HttpServletRequest request) {
        StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), error.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonServiceException(AmazonServiceException error, HttpServletRequest request) {
        HttpStatus code = HttpStatus.valueOf(error.getErrorCode());
        StandardError erro = new StandardError(code.value(), error.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(code.value()).body(erro);
    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandardError> amazonClienteException(AmazonClientException error, HttpServletRequest request) {
        StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), error.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> amazonS3Exception(AmazonS3Exception error, HttpServletRequest request) {
        StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), error.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }


}
