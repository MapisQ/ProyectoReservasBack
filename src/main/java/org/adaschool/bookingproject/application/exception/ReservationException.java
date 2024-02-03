package org.adaschool.bookingproject.application.exception;

import org.adaschool.bookingproject.application.lasting.EMessage;
import org.springframework.http.HttpStatus;

public class ReservationException extends Exception {

    private HttpStatus status;
    private String message;

    public ReservationException(EMessage message) {
        this.status = message.getStatus();
        this.message = message.getMessage();
    }
}
