package com.qa.api.gorest.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest{
  @Test
  public void getAllUsers() 
  {
	  Response response = restclient.get(GOREST_URL, GOREST_ENDPOINT_USERS, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	  Assert.assertTrue(response.statusLine().contains("OK"));
  }
  @Test
  public void getAllUsers_with_query() 
  {
	 Map<String, String> qp = Map.of
			 ( "name","Chandrabhaga Pandey",
			   "gender","male"
			  );
	  
	  Response response = restclient.get(GOREST_URL, GOREST_ENDPOINT_USERS, qp, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	  Assert.assertTrue(response.statusLine().contains("OK"));
  }
  @Test
  public void getSingleUser() 
  {
	 
	  String userid="8045373";
	  Response response = restclient.get(GOREST_URL, GOREST_ENDPOINT_USERS+"/"+userid,null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	  Assert.assertTrue(response.statusLine().contains("OK"));
	  Assert.assertEquals(response.jsonPath().getString("id"),userid);
  }
}
