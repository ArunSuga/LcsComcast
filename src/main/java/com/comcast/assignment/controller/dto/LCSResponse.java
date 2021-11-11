package com.comcast.assignment.controller.dto;

import java.util.List;

public class LCSResponse {
	
	private List<Value> lcs ;
	
	public LCSResponse()
	{
		
	}
	
    public LCSResponse(List<Value> lcs) { 
		  super(); this.lcs = lcs;
    }
	 
	public List<Value> getLcs() {
		return lcs;
	}

	public void setLcs(List<Value> lcs) {
		this.lcs = lcs;
	}
}
