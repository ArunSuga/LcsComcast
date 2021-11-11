package com.comcast.assignment;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.comcast.assignment.controller.dto.LCSRequest;
import com.comcast.assignment.controller.dto.LCSResponse;
import com.comcast.assignment.controller.dto.Value;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class LcsComcastApplicationTests {
	
	@LocalServerPort
    int randomServerPort;	
	
	RestTemplate restTemplate;	
	LCSRequest lcsRequest;
	List<Value>  setOfStrings;
	LCSResponse lcsResponse ;
	ResponseEntity<LCSResponse> result;
	
	URI uri = null;
	
	@BeforeEach
	public void setup() throws URISyntaxException
	{   
		final String lcsUrl = "http://localhost:" + randomServerPort + "/lcs";
		uri = new URI(lcsUrl);
		restTemplate = new RestTemplate();
		lcsRequest   = new LCSRequest();
		setOfStrings = new ArrayList<Value>();
		lcsResponse  = new LCSResponse();
	}
	
	 /***
	 * Test cases to check the response when the input strings are given
	 * @throws URISyntaxException
	 */
	
	@Test
	public void testLCS() throws URISyntaxException
	{		
		Value value1 = new Value("comcast");
		Value value2 = new Value("communicate");
		Value value3 = new Value("commutation");
		
		setOfStrings.add(value1);
		setOfStrings.add(value2);
		setOfStrings.add(value3);
		
		lcsRequest.setSetOfStrings(setOfStrings);		
		HttpEntity<LCSRequest> request = new HttpEntity<>(lcsRequest);
        
        result = restTemplate.postForEntity(uri, request, LCSResponse.class);      
        Assertions.assertEquals(200, result.getStatusCodeValue());        
        List<Value> responseValues = result.getBody().getLcs();        
        Assertions.assertEquals(1,responseValues.size());        
        Assertions.assertEquals("com",responseValues.get(0).getValue());		
	}
	
	/**
	 * To test duplicate String
	 * @throws URISyntaxException
	 */
	@Test
	public void testLCSDuplicateString() throws URISyntaxException
	{		
		Value value1 = new Value("comcast");
		Value value2 = new Value("comcast");
		Value value3 = new Value("commutation");
		
		setOfStrings.add(value1);
		setOfStrings.add(value2);
		setOfStrings.add(value3);
		
		lcsRequest.setSetOfStrings(setOfStrings);		
		HttpEntity<LCSRequest> request = new HttpEntity<>(lcsRequest);	
		
		try
		{
			result = restTemplate.postForEntity(uri, request, LCSResponse.class);
		}
		catch(HttpClientErrorException  ex)
		{
			Assertions.assertEquals(400,ex.getRawStatusCode());        
		}
              
	}
	
	@Test
	public void testLCSForEmptySetOfStrings() throws URISyntaxException
	{
			
		lcsRequest.setSetOfStrings(setOfStrings);		
		HttpEntity<LCSRequest> request = new HttpEntity<>(lcsRequest);	
		
		try
		{
			result = restTemplate.postForEntity(uri, request, LCSResponse.class);
		}
		catch(HttpClientErrorException  ex)
		{
			Assertions.assertEquals(400,ex.getRawStatusCode());        
		}              
	}
	
	@Test
	public void testLCSForEmptyValues() throws URISyntaxException
	{
		Value value1 = new Value("comcast");
		Value value2 = new Value("");
		Value value3 = new Value("commutation");
		
		setOfStrings.add(value1);
		setOfStrings.add(value2);
		setOfStrings.add(value3);	
		lcsRequest.setSetOfStrings(setOfStrings);		
		HttpEntity<LCSRequest> request = new HttpEntity<>(lcsRequest);	
		
		try
		{
			result = restTemplate.postForEntity(uri, request, LCSResponse.class);
		}
		catch(HttpClientErrorException  ex)
		{
			Assertions.assertEquals(400,ex.getRawStatusCode());        
		}              
	}
	
	@Test
	public void testLCSForNonJsonInput() throws URISyntaxException
	{
	
		HttpEntity<String> request = new HttpEntity<>("FailTest");	
		
		try
		{
			result = restTemplate.postForEntity(uri, request, LCSResponse.class);
		}
		catch(HttpClientErrorException  ex)
		{
			Assertions.assertEquals(415,ex.getRawStatusCode());        
		}              
	}

}
