package com.example.testtask.aop;

import com.example.testtask.exception.EntityNotFoundException;
import com.example.testtask.dto.ResponseDto;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ResponseDto> handleNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.badRequest().body(new ResponseDto(e.getMessage()));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleConstraintViolationException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(e.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList()));
    }
}
