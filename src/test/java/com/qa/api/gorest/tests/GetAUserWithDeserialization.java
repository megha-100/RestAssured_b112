package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.JsonUtils;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAUserWithDeserialization extends BaseTest {
	private String token;
	 @BeforeClass
	  public void getToken() 
	  {
		  token="14f8293c5d87420cc40e6689a82f2856b454374087a3035b472ae1c77f31bad1";
		  ConfigManager.set("BearerToken", token);
		 
	  }
  @Test
  public void getAUserWithDeserialization() 
  {
	  User user=new User(null,"Priyanka",StringUtil.getRandomEmails(),"female","active");
		 Response response = restclient.post(GOREST_URL, GOREST_ENDPOINT_USERS,user, null, null,AuthType.BEARER_TOKEN,ContentType.JSON);
		 Assert.assertNotNull(response.jsonPath().getString("id"));
		 Assert.assertEquals(response.jsonPath().getString("name"),"Priyanka");
		 
		 String userid=response.jsonPath().getString("id");
		Response getresponse = restclient.get(GOREST_URL, GOREST_ENDPOINT_USERS+"/"+userid, null, null,AuthType.BEARER_TOKEN,ContentType.JSON);
		 Assert.assertTrue(getresponse.statusLine().contains("OK"));
			 //deserilization
		
		User userdes = JsonUtils.deserialize(getresponse, User.class);
		 Assert.assertEquals(userdes.getName(), user.getName());

			
  }
}
