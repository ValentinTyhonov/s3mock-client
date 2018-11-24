package com.vtyhonov.s3mockclient.exceptions;

public class FileDoesNotExistException extends RuntimeException {

    public FileDoesNotExistException(String msg) {
        super(msg);
    }
}
