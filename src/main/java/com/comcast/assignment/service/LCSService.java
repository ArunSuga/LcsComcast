package com.comcast.assignment.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.comcast.assignment.controller.dto.LCSResponse;
import com.comcast.assignment.controller.dto.Value;
import com.comcast.assignment.exception.RequestNotValidException;

@Service
public class LCSService {
	
	public LCSResponse getLCS(Set<String> setOfStrings) {
		// TODO Auto-generated method stub
		
		List<Set> subStringList = new ArrayList();
		
		//find the substring using each string
		setOfStrings.forEach(inputString ->
		{
			Set<String> subStringSet = getSubStringList(inputString);
			subStringList.add(subStringSet);
		});
			
		//Get the first substring list and compare it to other list 
		//and get the matching substring values

		Set<String> firstList = subStringList.get(0);		
		//System.out.println("firstList:" + firstList.toString());

		List<String> matchingList = getMatchingSubString(subStringList, firstList);		
		
		//Get the largest list from the matching list
		List<String> largestString = getLargestStringList(matchingList);
		Collections.sort(largestString);
		
		LCSResponse lcsResponse = getLCSResponse(largestString);		
		
		return lcsResponse;
	}

	public Set<String> validateAndGetInput(List<Value> setOfValues, Set<String> setOfStrings) throws RequestNotValidException {
		
		for(Value value:setOfValues)
        {   
			 String inputString = ((Value)value).getValue();
							 
			 if(inputString.isEmpty() || inputString == null) {				 
				 throw new RequestNotValidException("value should not be null or empty");
			 }
			 
			 if(setOfStrings.contains(inputString))
			 {				 
			  throw new RequestNotValidException("Value of a string should be unique and not repeated");				
			 }
			 setOfStrings.add(inputString);
			 
 		 }
		
		return setOfStrings;
	
	}

	private LCSResponse getLCSResponse(List<String> largestString) {
		
		Collections.sort(largestString);
		
		LCSResponse lcsResponse = new LCSResponse();
		List<Value> responseValues = new ArrayList();		
		largestString.forEach(outputString -> {			
			Value value = new Value();
			value.setValue(outputString);
			responseValues.add(value);			
		});
		
		lcsResponse.setLcs(responseValues);
		
		return lcsResponse;
	}

	private List<String> getLargestStringList(List<String> matchingList) {
		
		List<String> largestString = new ArrayList();			
		int largestlength = 0;
		  for(String outString: matchingList ){	
			  
			  if (outString.length() >= largestlength) 
			  {
				  if(! largestString.isEmpty() && largestString.get(0).length() < outString.length())
				  {
					  largestString.clear();
					  largestString.add(outString);
				  }
				  else if(largestString.isEmpty() || largestString.get(0).length() == outString.length())
				  {
					  largestString.add(outString);
				  }				  
			  }
			  largestlength = outString.length();
		  }
		  return largestString;
	}

	private List<String> getMatchingSubString(List<Set> subStringList, Set<String> firstList) {
		
		List<String> matchingList = new ArrayList<String>();

		for (String refElement : firstList)
		{
			boolean elementfound = false;
		
			for (int i = 1; i < subStringList.size(); i++) {

				Set<String> nextList = subStringList.get(i);

				if (nextList.contains(refElement))
				{
					elementfound = true;
				} else
				{
					elementfound = false;
					break;					
				}
			}

			if (elementfound) {
				matchingList.add(refElement);
			}

		}
		return matchingList;
	}
	
	private static Set<String> getSubStringList(String s) {
		Set<String> subset = new HashSet<String>();
		
		for(int i=0 ; i<s.length(); i++)
		{
			for(int j = i+1 ; j<=s.length() ; j++)
			{
				subset.add(s.substring(i,j));
			}	
		}		
		return subset;
	} 

}
