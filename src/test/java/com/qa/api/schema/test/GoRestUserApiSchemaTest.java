package com.qa.api.schema.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtil;

import io.restassured.module.jsv.JsonSchemaValidator;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRestUserApiSchemaTest extends BaseTest{
  @Test
  public void getuserScheme() {
	 ConfigManager.set("BearerToken", "14f8293c5d87420cc40e6689a82f2856b454374087a3035b472ae1c77f31bad1");
	  Response response = restclient.get(GOREST_URL, GOREST_ENDPOINT_USERS, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
	  Assert.assertTrue(SchemaValidator.validateschema(response, "schema/getuser.json"));
  }
  @Test
  public void createuserScheme() {
	 //ConfigManager.set("BearerToken", "14f8293c5d87420cc40e6689a82f2856b454374087a3035b472ae1c77f31bad1");
	 // Response response = restclient.get(GOREST_URL, GOREST_ENDPOINT_USERS, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
	 // Assert.assertTrue(SchemaValidator.validateschema(response, "schema/getusers.json"));
	 User user = User.builder()
	  .name("megha")
	  .email(StringUtil.getRandomEmails())
	  .status("active")
	  .gender("female")
	  .build();
	  
	  Response response = restclient.post(GOREST_URL, GOREST_ENDPOINT_USERS, user, null, null, AuthType.BEARER_TOKEN,ContentType.JSON );
	 Assert.assertTrue(SchemaValidator.validateschema(response, "schema/createUserJsonScheme.json"));

  }
}
