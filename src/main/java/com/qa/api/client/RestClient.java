package com.qa.api.client;

import java.io.File;
import java.util.Base64;
import java.util.Map;

import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.APIExceptions;
//import com.qa.api.exceptions.FrameWorkExceptions;
import com.qa.api.manager.ConfigManager;

//import com.qa.api.constants.ContentType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.expect;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

public class RestClient 
{
	//define responsespec
	private ResponseSpecification responsespec200=expect().statusCode(200);
	private ResponseSpecification responsespec201=expect().statusCode(201);
	private ResponseSpecification responsespec204=expect().statusCode(204);
	private ResponseSpecification responsespec404=expect().statusCode(404);
	private ResponseSpecification responsespec200or201=expect().statusCode(anyOf(equalTo(200),equalTo(201)));
	private ResponseSpecification responsespec200or404=expect().statusCode(anyOf(equalTo(200),equalTo(404)));
	

	
     private RequestSpecification setup_request(String URL,AuthType auth,ContentType content_type)
{
	RequestSpecification rs = RestAssured.given().log().all()
	    .baseUri(URL)
	    .contentType(content_type)
	    
	    .accept(content_type);
	    
	switch(auth)
	{
	case BEARER_TOKEN:rs.header("Authorization","Bearer "+ConfigManager.get("BearerToken"));
	break;
	case OAUTH2:rs.header("Authorization","Bearer"+" oauth token");
		break;
	case API_KEY:rs.header("x-api-key",ConfigManager.get("x-api-key"));
		break;
	case BASIC_AUTH:rs.header("Authorization","Basic "+ generatebasic_auth_token());//it returns base64 encoded form of un and pw-un:pw
	//rs.auth().basic(ConfigManager.get("Basicauthusername"), ConfigManager.get("BasicauthPassword"));
	break;
	case NO_AUTH:System.out.println("no auth required");
	break;
	default:System.out.println("this type of auth is not supported");
	throw new APIExceptions("invalid auth");
	
	}
	
	return rs;
	
	
	}
     
    
private void applyparams(RequestSpecification rs,Map<String,String> queryparam,Map<String,String> pathparam)
{
	if(queryparam!=null)
			rs.queryParams(queryparam);
	if(pathparam!=null)
		rs.pathParams(pathparam);
}
private String generatebasic_auth_token()
{
	String token=ConfigManager.get("Basicauthusername")+":"+ConfigManager.get("BasicauthPassword");
	//admin:admin-base64encoder
	//base64 encoder-b to a
	return Base64.getEncoder().encodeToString(token.getBytes());
}

//CRUD
//creating our own get method
//GET
/**
 * this is to return get call API response
 * @param baseUrl
 * @param endpoint
 * @param queryparam
 * @param pathparam
 * @param auth
 * @param content_type
 * @return
 */
public Response get(String baseUrl,String endpoint,Map<String,String> queryparam,Map<String,String> pathparam,AuthType auth,ContentType content_type)
{
	RequestSpecification rs=setup_request(baseUrl,auth,content_type);
	applyparams(rs,queryparam,pathparam);
	//we are validate and returning the whole response
	Response response = rs.when()
	    .get(endpoint)
	     .then().log().all()
	       .spec(responsespec200or404)
	        .extract()
	         .response();
	response.prettyPrint();
	return response;
}

//post
public <T> Response post(String baseUrl,String endpoint,T body,Map<String,String> queryparam,Map<String,String> pathparam,AuthType auth,ContentType content_type)
{
	RequestSpecification rs=setup_request(baseUrl,auth,content_type);
	applyparams(rs,queryparam,pathparam);
	//we are validate and returning the whole response
	Response response = rs.body(body)
	    .when()
	     .post(endpoint)
	      .then().log().all()
	       .spec(responsespec200or201)
	        .extract()
	         .response();
	response.prettyPrint();
	return response;
}
//file type -method overloading
public Response post(String baseUrl,String endpoint,File body,Map<String,String> queryparam,Map<String,String> pathparam,AuthType auth,ContentType content_type)
{
	RequestSpecification rs=setup_request(baseUrl,auth,content_type);
	applyparams(rs,queryparam,pathparam);
	//we are validate and returning the whole response
	Response response = rs.body(body)
	    .when()
	     .post(endpoint)
	      .then().log().all()
	       .spec(responsespec200or201)
	        .extract()
	         .response();
	response.prettyPrint();
	return response;
}
//when body is of x-www-form-urlencoded
public Response post(String baseUrl,String endpoint,String clientId, String clientsecret ,String granttype,ContentType content_type)
{
	
	
	//we are validate and returning the whole response
	
	Response response = RestAssured.given()
			.formParam("grant_type",granttype)
			   .formParam("client_id",clientId)
			   .formParam("client_secret",clientsecret)
			.contentType(content_type)
	    .when()
	     .post(baseUrl+endpoint);
	     
	      
	response.prettyPrint();
	return response;
}

public <T> Response put(String baseUrl,String endpoint,T body,Map<String,String> queryparam,Map<String,String> pathparam,AuthType auth,ContentType content_type)
{
	RequestSpecification rs=setup_request(baseUrl,auth,content_type);
	applyparams(rs,queryparam,pathparam);
	//we are validate and returning the whole response
	Response response = rs.body(body)
	    .when()
	     .put(endpoint)
	      .then().log().all()
	       .spec(responsespec200)
	        .extract()
	         .response();
	response.prettyPrint();
	return response;
}

public <T>Response patch(String baseUrl,String endpoint,T body,Map<String,String> queryparam,Map<String,String> pathparam,AuthType auth,ContentType content_type)
{
	RequestSpecification rs=setup_request(baseUrl,auth,content_type);
	applyparams(rs,queryparam,pathparam);
	//we are validate and returning the whole response
	Response response = rs.body(body)
	    .when()
	     .patch(endpoint)
	      .then().log().all()
	       .spec(responsespec200)
	        .extract()
	         .response();
	response.prettyPrint();
	return response;
}

public Response delete(String baseUrl,String endpoint,Map<String,String> queryparam,Map<String,String> pathparam,AuthType auth,ContentType content_type)
{
	RequestSpecification rs=setup_request(baseUrl,auth,content_type);
	applyparams(rs,queryparam,pathparam);
	//we are validate and returning the whole response
	Response response = rs
	    .when()
	     .delete(endpoint)
	      .then().log().all()
	       .spec(responsespec204)
	        .extract()
	         .response();
	response.prettyPrint();
	return response;
}
	
}

