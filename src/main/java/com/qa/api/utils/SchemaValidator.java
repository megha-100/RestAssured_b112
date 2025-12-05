package com.qa.api.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class SchemaValidator {
	
public static boolean validateschema(Response response,String schemafilename)
{
	try {
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemafilename));
		System.out.println("schema pass"+schemafilename);
		return true;
	}
	catch(Exception e)
	{
		System.out.println("schema fail "+e.getMessage());
		return false;
	}
}
}
