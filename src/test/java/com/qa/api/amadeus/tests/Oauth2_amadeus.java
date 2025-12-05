package com.qa.api.amadeus.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

public class Oauth2_amadeus extends BaseTest {
	 private String token;
  @Test
  public void getflightdetails() 
  {
	 Map<String, String> qp = Map.of(
		"origin","PAR",
		"maxPrice","200"
			  );
	 Response response =  restclient.get(OAUTH2_AMADEUS_URL, AMADEUS_FLIGHT_DESTINATIONS_ENDPOINT, qp, null, AuthType.BEARER_TOKEN, ContentType.ANY);
	 Assert.assertEquals(response.getStatusCode(), 200);
  }
  
  @BeforeMethod
  public void getAccessToken() 
 {
	 
//	 Map<String, String> fm = Map.of(
//			"ClientId" ,ConfigManager.get("ClientId"),
//			"ClientSecret",ConfigManager.get("ClientSecret"),
//			"granttype",ConfigManager.get("granttype")
//			  );
	 //Response response = restclient.post(OAUTH2_AMADEUS_URL, OAUTH2_AMADEUS_ACCESSTOKEN_ENDPOINT, fm, ContentType.URLENC);
	 //response.prettyPrint();
  
	  Response response =  restclient.post(OAUTH2_AMADEUS_URL, OAUTH2_AMADEUS_ACCESSTOKEN_ENDPOINT,ConfigManager.get("ClientId") , ConfigManager.get("ClientSecret"),ConfigManager.get("granttype") , ContentType.URLENC);
	  response.prettyPrint();
	  token= response.jsonPath().getString("access_token");
	  //we need to set the configmanager BearerToken to the latest one 
	  ConfigManager.set("BearerToken", token);
 }

}
