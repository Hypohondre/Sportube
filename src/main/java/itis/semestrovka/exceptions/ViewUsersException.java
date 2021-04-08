package itis.semestrovka.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ViewUsersException extends Exception{
    public ViewUsersException(String message){super(message);}
}
