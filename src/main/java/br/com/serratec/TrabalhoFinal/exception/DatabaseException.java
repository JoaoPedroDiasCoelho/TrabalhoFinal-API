package br.com.serratec.TrabalhoFinal.exception;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String msg) {
        super(msg);
    }
}