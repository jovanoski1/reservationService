package com.CarRent.reservationService.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ErrorDetails {
    @JsonProperty("error_code")
    private ErrorCode errorCode;
    @JsonProperty("error_message")
    private String errorMessage;
    private Instant timestamp;

    public ErrorDetails() {

    }

    public ErrorDetails(ErrorCode errorCode, String errorMessage, Instant timestamp) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

}
