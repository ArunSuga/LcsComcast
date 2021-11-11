package com.comcast.interview.controller.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

public class LCSRequest {
	
	@NotEmpty(message = "setOfStrings should not be null or empty")
	private List<Value> setOfStrings;
	
	public LCSRequest()
	{
		
	}
	
	
	public LCSRequest(List<Value> setOfStrings) {
		super();
		this.setOfStrings = setOfStrings;
	}
	
	public List<Value> getSetOfStrings() {
		return setOfStrings;
	}
	
	public void setSetOfStrings(List<Value> setOfStrings) {
		this.setOfStrings = setOfStrings;
	}
}
