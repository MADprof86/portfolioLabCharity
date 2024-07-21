package pl.coderslab.charity.exceptions;

public class PasswordMismatchException extends Exception {
    public PasswordMismatchException(String message){
        super(message);
    }
}
