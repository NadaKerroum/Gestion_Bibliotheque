package com.example.Gestion_Bibliotheque.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailDejeExistantException  extends RuntimeException{
	public EmailDejeExistantException (String message ) {
		super(message);
	}

}
