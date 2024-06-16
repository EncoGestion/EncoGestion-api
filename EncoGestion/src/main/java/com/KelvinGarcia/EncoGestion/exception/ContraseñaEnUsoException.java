package com.KelvinGarcia.EncoGestion.exception;

public class ContraseñaEnUsoException extends RuntimeException {

    public ContraseñaEnUsoException() {
    }

    public ContraseñaEnUsoException(String message) {
        super(message);
    }
}
