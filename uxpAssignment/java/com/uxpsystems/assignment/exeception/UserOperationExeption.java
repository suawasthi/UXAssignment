package com.uxpsystems.assignment.exeception;

import lombok.Getter;
import lombok.Setter;

public class UserOperationExeption  extends Exception{

	
	@Getter
	@Setter
	private String message;
	UserOperationExeption(String customMessage){
		super();
		this.message=customMessage;
	}
	
	
}
