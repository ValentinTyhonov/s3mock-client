package com.vtyhonov.s3mockclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseDto {

    @JsonProperty(value = "status code")
    private int statusCode;

    @JsonProperty(value = "message")
    private String message;
}
