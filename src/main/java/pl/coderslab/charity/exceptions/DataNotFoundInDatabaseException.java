package pl.coderslab.charity.exceptions;

public class DataNotFoundInDatabaseException extends Exception{
    public  DataNotFoundInDatabaseException(String message){
       super(message);
    }
}
