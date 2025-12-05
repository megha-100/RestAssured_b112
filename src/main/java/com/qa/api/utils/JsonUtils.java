package com.qa.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class JsonUtils
{

	private static ObjectMapper objmap=new ObjectMapper();
	public static <T> T deserialize(Response response,Class<T> targetclass)
	{
		try {
			return objmap.readValue(response.getBody().asString(), targetclass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("deserialization is failed"+targetclass.getName());
		}
	}
}
