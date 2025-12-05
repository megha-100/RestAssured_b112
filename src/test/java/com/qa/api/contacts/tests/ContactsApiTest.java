package com.qa.api.contacts.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.Contacts;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactsApiTest extends BaseTest{
	private String token;
  @BeforeMethod
  public void getToken() 
  {
	  Contacts contacts = Contacts.builder()
	          .email("naveenanimation20@gmail.com")
	          .password("test@123")
	          .build();
	  Response response = restclient.post(CONTACTS_URL, CONTACTS_LOGIN_ENDPOINT,contacts ,null, null, AuthType.NO_AUTH, ContentType.JSON);
	  
	  token = response.jsonPath().getString("token");
	  System.out.println("token=="+token);
	  Assert.assertEquals(response.statusCode(), 200);
	  ConfigManager.set("BearerToken", token);
  }
  
  @Test
  public void getAllContacts() 
  {
	 
	  Response response = restclient.get(CONTACTS_URL, CONTACTS_ENDPOINT ,null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	  response.prettyPrint();
	  Assert.assertEquals(response.statusCode(), 200);

//	  token = response.jsonPath().getString("token");
//	  System.out.println("token=="+token);
  }
}
