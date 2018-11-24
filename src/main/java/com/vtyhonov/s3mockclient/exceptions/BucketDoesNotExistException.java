package com.vtyhonov.s3mockclient.exceptions;

public class BucketDoesNotExistException extends RuntimeException {

    public BucketDoesNotExistException(String msg) {
        super(msg);
    }
}
