package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest{
  @Test
  public void deleteUserTest()
  {
	  User user= User.builder()
		       .name("veena")
		       .email(StringUtil.getRandomEmails())
		       .gender("female")
		       .status("active")
		       .build();
		 //1.post
		  Response postresponse = restclient.post(GOREST_URL, GOREST_ENDPOINT_USERS, user, null, null,AuthType.BEARER_TOKEN,ContentType.JSON);
		  Assert.assertNotNull(postresponse.jsonPath().getString("id"));
			 Assert.assertEquals(postresponse.jsonPath().getString("name"),"veena");
			 String userid = postresponse.jsonPath().getString("id");
			 System.out.println("userid=="+userid);
			 
	    //2.get-get the user with the same userid
			 Response getresponse = restclient.get(GOREST_URL, GOREST_ENDPOINT_USERS+"/"+userid, null, null,AuthType.BEARER_TOKEN,ContentType.JSON);
			 Assert.assertTrue(getresponse.statusLine().contains("OK"));
				 Assert.assertEquals(getresponse.jsonPath().getString("id"),userid);
		 //3.delete-delete the user with the same userid
				 Response deleteresponse = restclient.delete(GOREST_URL, GOREST_ENDPOINT_USERS+"/"+userid, null, null,AuthType.BEARER_TOKEN,ContentType.JSON);
				 Assert.assertTrue(deleteresponse.statusLine().contains("No Content"));
		 //4.get-get the user with the same userid
				 getresponse = restclient.get(GOREST_URL, GOREST_ENDPOINT_USERS+"/"+userid, null, null,AuthType.BEARER_TOKEN,ContentType.JSON);
				 Assert.assertTrue(getresponse.statusLine().contains("Not Found"));
				 Assert.assertEquals(getresponse.jsonPath().getString("message"),"Resource not found"); 		
				
  }
}
