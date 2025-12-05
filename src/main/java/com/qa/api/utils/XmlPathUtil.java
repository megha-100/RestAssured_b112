package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XmlPathUtil 
{
	private static XmlPath getresponse_as_string(Response response)
	{
		String strresponse = response.body().asString();
		XmlPath xm=new XmlPath(strresponse);
	
		return xm;
	}
//50.05 time
	public static <T>T read(Response response,String xmlPath)//$.id
	{
		
		XmlPath strresponse = getresponse_as_string(response);
		return strresponse.get(xmlPath);
		
		
	}
	public static <T> List<T> readList(Response response,String xmlPathexp)//$.id
	{
		
		XmlPath strresponse = getresponse_as_string(response);
		return strresponse.getList(xmlPathexp);
		
	}
	public static <T>List<Map<String,T>>readListOfMap(Response response,String xmlPathexp)//$.id
	{
		
		XmlPath strresponse = getresponse_as_string(response);
		return strresponse.getList(xmlPathexp);
		//   or
		//return response.xmlPath().getList(xmlPathexp);
	
		
	}

}
