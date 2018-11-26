package com.vtyhonov.s3mockclient.exceptions;

public class DownloadFileException extends RuntimeException {

    public DownloadFileException(Throwable throwable) {
        super(throwable);
    }
}
