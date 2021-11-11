package com.comcast.interview.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.comcast.interview.controller.dto.LCSRequest;
import com.comcast.interview.controller.dto.LCSResponse;
import com.comcast.interview.controller.dto.Value;
import com.comcast.interview.service.LCSService;


@RestController
@Validated
public class LCSController {
	
	@Autowired
	private LCSService lcsService;
	
	@PostMapping(value= "/lcs" ,consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object>  findLCS(@Valid  @RequestBody LCSRequest lcsRequest) throws Exception
	{	
		
		List<Value> values = lcsRequest.getSetOfStrings();
		
		Set<String> setOfStrings = new HashSet<String>();
		
		setOfStrings = lcsService.validateAndGetInput(values , setOfStrings);
				
		LCSResponse response = lcsService.getLCS(setOfStrings);
				
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
}
