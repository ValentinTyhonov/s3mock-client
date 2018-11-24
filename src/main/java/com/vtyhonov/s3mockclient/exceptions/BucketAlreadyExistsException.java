package com.vtyhonov.s3mockclient.exceptions;

public class BucketAlreadyExistsException extends RuntimeException {

    public BucketAlreadyExistsException(String msg) {
        super(msg);
    }
}
