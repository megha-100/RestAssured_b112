package com.qa.api.products.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidatorUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPItest_with_api_test extends BaseTest 
{
	//50.05time
  @Test
  public void getproducttest()
  {
	  Response response = restclient.get(FAKETOSTORE_PRODUCTS_URL, FAKETOSTORE_PRODUCTS_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
	 Assert.assertEquals(response.statusCode(), 200);
//	 String strresponse=response.asString();
	// DocumentContext ctx = JsonPath.parse(strresponse);
	 
	 List<Number> prices = JsonPathValidatorUtility.readList(response, "$[?(@.price>50)].price");
	 System.out.println(prices);
	 
	 List<Number> ids = JsonPathValidatorUtility.readList(response, "$[?(@.price>50)].id");
	 System.out.println(ids);
	 
	 List<Number> rates = JsonPathValidatorUtility.readList(response, "$[?(@.price>50)].rating.rate");
	 System.out.println(rates);
	 
	 List<Integer> counts = JsonPathValidatorUtility.readList(response, "$[?(@.price>50)].rating.count");
	 System.out.println(counts);
	 
	 List<Map<String, Object>> ids_and_titles = JsonPathValidatorUtility.readListOfMap(response,"$[*].['id','title']");
	 System.out.println(ids_and_titles);
	 
	 List<Map<String, Object>> ids_and_titles_and_category = JsonPathValidatorUtility.readListOfMap(response,"$[*].['id','title','category']");
	 System.out.println(ids_and_titles_and_category);
	 
	 List<Map<String, Object>> ids_and_titles1 = JsonPathValidatorUtility.readListOfMap(response,"$[?(@.category=='jewelery')].['id','title']");
	 System.out.println(ids_and_titles1);
	 
	 Double minPrice = JsonPathValidatorUtility.read(response,"min($[*].price)");
	 System.out.println(minPrice);
  }
}
