package org.example.discussionrest.controller;

import org.example.discussionrest.dto.ExceptionDto;
import org.example.discussionrest.exception.RecordNotFoundException;
import org.example.discussionrest.exception.UserAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public @ResponseBody ExceptionDto handleRecordNotFoundException(RecordNotFoundException ex) {
        return buildExceptionDto(ex, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public @ResponseBody ExceptionDto handleUserAlreadyRegisteredException(UserAlreadyRegisteredException ex) {
        return buildExceptionDto(ex, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public @ResponseBody ExceptionDto handleException(Exception ex) {
        return buildExceptionDto(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ExceptionDto buildExceptionDto(Exception ex, HttpStatus status) {
        return ExceptionDto.builder()
                .status(status.value())
                .message(ex.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
