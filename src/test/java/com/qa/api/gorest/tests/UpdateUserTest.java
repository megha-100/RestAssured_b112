package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserTest extends BaseTest {
  @Test
  public void update_user_test() 
  {
	 User user= User.builder()
	       .name("sandu")
	       .email(StringUtil.getRandomEmails())
	       .gender("male")
	       .status("active")
	       .build();
	 //1.post
	  Response postresponse = restclient.post(GOREST_URL, GOREST_ENDPOINT_USERS, user, null, null,AuthType.BEARER_TOKEN,ContentType.JSON);
	  Assert.assertNotNull(postresponse.jsonPath().getString("id"));
		 Assert.assertEquals(postresponse.jsonPath().getString("name"),"sandu");
		 String userid = postresponse.jsonPath().getString("id");
		 System.out.println("userid=="+userid);
		 
    //2.get-get the user with the same userid
		 Response getresponse = restclient.get(GOREST_URL, GOREST_ENDPOINT_USERS+"/"+userid, null, null,AuthType.BEARER_TOKEN,ContentType.JSON);
		 Assert.assertTrue(getresponse.statusLine().contains("OK"));
			 Assert.assertEquals(getresponse.jsonPath().getString("id"),userid);
			
	 //3.put-get the user with the same userid
			 user.setName("banupriya");
			 user.setGender("female");
			 Response putresponse = restclient.put(GOREST_URL, GOREST_ENDPOINT_USERS+"/"+userid,user, null, null,AuthType.BEARER_TOKEN,ContentType.JSON);
			  Assert.assertTrue(putresponse.statusLine().contains("OK"));
			  
			  Assert.assertEquals(putresponse.jsonPath().getString("id"),userid);
				 Assert.assertEquals(putresponse.jsonPath().getString("name"),"banupriya");
				 Assert.assertEquals(putresponse.jsonPath().getString("gender"),"female");
				 
	//4.get-get the user with the same userid
						 getresponse = restclient.get(GOREST_URL, GOREST_ENDPOINT_USERS+"/"+userid, null, null,AuthType.BEARER_TOKEN,ContentType.JSON);
						 Assert.assertTrue(getresponse.statusLine().contains("OK"));
							 Assert.assertEquals(getresponse.jsonPath().getString("id"),userid);	
							 Assert.assertEquals(getresponse.jsonPath().getString("name"),"banupriya");
							 Assert.assertEquals(getresponse.jsonPath().getString("gender"),"female");
  }
}
