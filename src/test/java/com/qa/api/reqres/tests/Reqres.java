package com.qa.api.reqres.tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Reqres extends BaseTest{
	
	
  @Test
  public void getusers() 
  {
	  HashMap<String,String>qp=new HashMap<String,String>();
	  qp.put("page","2");
		 Response response = restclient.get(REQRES_URL, REQRES_USERS_ENDPOINT,qp , null,AuthType.NO_AUTH,ContentType.ANY);
		 Assert.assertEquals(response.statusCode(),200);
  }
}
