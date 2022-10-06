package br.com.cefops.SendMenssage.storage.exception;

public class IncorrectFileNameException extends Exception {
    public IncorrectFileNameException(String errorMessage) {
        super(errorMessage);
    }
}