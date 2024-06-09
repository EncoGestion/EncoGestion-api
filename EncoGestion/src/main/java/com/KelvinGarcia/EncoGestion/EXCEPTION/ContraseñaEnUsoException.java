package com.KelvinGarcia.EncoGestion.EXCEPTION;

public class ContraseñaEnUsoException extends RuntimeException {

    public ContraseñaEnUsoException() {
    }

    public ContraseñaEnUsoException(String message) {
        super(message);
    }
}
