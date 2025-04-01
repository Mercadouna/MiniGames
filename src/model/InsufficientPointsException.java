package model;

// Excepción personalizada para cuando un jugador no tiene puntos suficientes
public class InsufficientPointsException extends Exception {

    public InsufficientPointsException(String message) {
        super(message);
    }

    public InsufficientPointsException(String message, Throwable cause) {
        super(message, cause);
    }
}