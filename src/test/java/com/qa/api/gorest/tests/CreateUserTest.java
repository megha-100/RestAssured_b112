package com.qa.api.gorest.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.Contacts;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest{
	private String token;
	 @BeforeClass
	  public void getToken() 
	  {
		  token="14f8293c5d87420cc40e6689a82f2856b454374087a3035b472ae1c77f31bad1";
		  ConfigManager.set("BearerToken", token);
	  }
	 @Test
	  public void createAUserTest() 
	 {
		 User user=new User(null,"Priyanka",StringUtil.getRandomEmails(),"female","active");
		 Response response = restclient.post(GOREST_URL, GOREST_ENDPOINT_USERS,user, null, null,AuthType.BEARER_TOKEN,ContentType.JSON);
		 Assert.assertNotNull(response.jsonPath().getString("id"));
		 Assert.assertEquals(response.jsonPath().getString("name"),"Priyanka");
	  }
	 @Test
	  public void createAUserTest_withStringBody() 
	 {
		String user="{\r\n"
				+ "    \"name\": \"Megha\",\r\n"
				+ "    \"email\": \"megha@kutch208.test\",\r\n"
				+ "    \"gender\": \"female\",\r\n"
				+ "    \"status\": \"active\"\r\n"
				+ "}";
		 Response response = restclient.post(GOREST_URL, GOREST_ENDPOINT_USERS,user, null, null,AuthType.BEARER_TOKEN,ContentType.JSON);
		 Assert.assertNotNull(response.jsonPath().getString("id"));
		 Assert.assertEquals(response.jsonPath().getString("name"),"Megha");
	  }
	 @Test
	  public void createAUserTest_withJsonFileBody() 
	 {
		File file=new File("./src/test/resources/jsons/user.json");
		 Response response = restclient.post(GOREST_URL, GOREST_ENDPOINT_USERS,file, null, null,AuthType.BEARER_TOKEN,ContentType.JSON);
		 Assert.assertNotNull(response.jsonPath().getString("id"));
		 Assert.assertEquals(response.jsonPath().getString("name"),"Tom");
	  }

}
