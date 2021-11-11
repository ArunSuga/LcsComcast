package com.comcast.assignment.controller.dto;

import javax.validation.constraints.NotEmpty;

public class Value {
	
	@NotEmpty(message = "value should not be null or empty")
	private String value;
	
	public Value()
	{
		
	}

	public Value(String value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
