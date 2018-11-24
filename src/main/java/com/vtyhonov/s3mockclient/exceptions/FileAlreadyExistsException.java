package com.vtyhonov.s3mockclient.exceptions;

public class FileAlreadyExistsException extends RuntimeException {

    public FileAlreadyExistsException(String msg) {
        super(msg);
    }
}
