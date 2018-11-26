package com.vtyhonov.s3mockclient.controller.handler;

import com.vtyhonov.s3mockclient.dto.ResponseDto;
import com.vtyhonov.s3mockclient.exceptions.BucketAlreadyExistsException;
import com.vtyhonov.s3mockclient.exceptions.BucketDoesNotExistException;
import com.vtyhonov.s3mockclient.exceptions.DownloadFileException;
import com.vtyhonov.s3mockclient.exceptions.FileAlreadyExistsException;
import com.vtyhonov.s3mockclient.exceptions.FileDoesNotExistException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler {

    @ExceptionHandler({BucketAlreadyExistsException.class, FileAlreadyExistsException.class})
    public ResponseEntity handleBadRequestException(final RuntimeException exception, final WebRequest request) {
        ResponseDto response = new ResponseDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BucketDoesNotExistException.class, FileDoesNotExistException.class})
    public ResponseEntity handleNotFoundException(final RuntimeException exception, final WebRequest request) {
        ResponseDto response = new ResponseDto(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DownloadFileException.class})
    public ResponseEntity handleInternalServerErrorException(final RuntimeException exception, final WebRequest request) {
        ResponseDto response = new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
