package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonPathValidatorUtility
{
	private static String getresponse_as_string(Response response)
	{
		return response.getBody().asString();
		// DocumentContext ctx = JsonPath.parse(response);
	}
//50.05 time
	public static <T>T read(Response response,String jsonPath)//$.id
	{
		
		String strresponse = getresponse_as_string(response);
		return JsonPath.read(strresponse,jsonPath);
		//ctx.read(jsonPath)
	}
	public static <T> List<T> readList(Response response,String jsonPath)//$.id
	{
		
		String strresponse = getresponse_as_string(response);
		return JsonPath.read(strresponse,jsonPath);
		//ctx.read(jsonPath)
	}
	public static <T>List<Map<String,T>>readListOfMap(Response response,String jsonPath)//$.id
	{
		
		String strresponse = getresponse_as_string(response);
		return JsonPath.read(strresponse,jsonPath);
	
		//ctx.read(jsonPath)
	}
}
