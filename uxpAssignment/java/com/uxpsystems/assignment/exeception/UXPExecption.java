package com.uxpsystems.assignment.exeception;

import lombok.Getter;
import lombok.Setter;

public class UXPExecption extends Exception {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String message;
	public UXPExecption(String customMessage){
		super();
		this.message=customMessage;
	}
	

}
