package com.qa.api.circuits.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.XmlPathUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CircuitsApiwithXmlTest extends BaseTest {
	 @Test
	  public void getCircuitinfoTest() 
	  {
		 Response response = restclient.get(XML_PATHTEST_URL, XML_PATHTESTS_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
		 
		List<String> titles = XmlPathUtil.readList(response, "bookstore.book.title");
		System.out.println(titles);
		
		for(String title:titles)
		{
			Assert.assertNotNull(title);
		}
		String price = XmlPathUtil.read(response,"**.findAll{ it.@category=='cooking' }.price");
		System.out.println(Double.parseDouble(price));

	  }

}
