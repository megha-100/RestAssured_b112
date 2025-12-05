package com.qa.api.basicauth.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.client.RestClient;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BasicAuth extends BaseTest{
  @Test
  public void basicauth()
  {
	  Response response = restclient.get(HEROKUAPP_URL, HEROKUAPP_ENDPOINT, null, null,AuthType.BASIC_AUTH, ContentType.ANY);
	  Assert.assertTrue(response.getBody().asString().contains(" Congratulations! You must have the proper credentials."));
	  Assert.assertEquals(response.statusCode(), 200);
	  
  }
}
