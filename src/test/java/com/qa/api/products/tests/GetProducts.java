package com.qa.api.products.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Products;
import com.qa.api.utils.JsonUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetProducts extends BaseTest{
  @Test
  public void getProducts() 
  {
	  Response response = restclient.get(FAKETOSTORE_PRODUCTS_URL, FAKETOSTORE_PRODUCTS_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
	 Products[] multileproducts = JsonUtils.deserialize(response, Products[].class);
	 for(Products p:multileproducts)
	 {
		 System.out.println("id="+p.getId());
			System.out.println("category="+p.getCategory());
			System.out.println("description="+p.getDescription());
			System.out.println("image="+p.getImage());
			System.out.println("price="+p.getPrice());
			System.out.println("rate="+p.getRating().getRate());
			System.out.println("count="+p.getRating().getCount());
			System.out.println();
	 }
  }
}
