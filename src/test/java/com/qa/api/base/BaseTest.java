package com.qa.api.base;

import org.testng.annotations.BeforeTest;

import com.qa.api.client.RestClient;

public class BaseTest {
	
	protected RestClient restclient;
	/*  all base urls */
	protected final static String GOREST_URL="https://gorest.co.in";
	protected final static String CONTACTS_URL="https://thinking-tester-contact-list.herokuapp.com";
	protected final static String REQRES_URL="https://reqres.in";
	protected final static String HEROKUAPP_URL="https://the-internet.herokuapp.com";
	protected final static String FAKETOSTORE_PRODUCTS_URL="https://fakestoreapi.com";
	protected final static String OAUTH2_AMADEUS_URL="https://test.api.amadeus.com";
	protected final static String XML_PATHTEST_URL="https://www.w3schools.com";

	/* all endpoint url*/
	protected final static String GOREST_ENDPOINT_USERS="public/v2/users";
	protected final static String CONTACTS_LOGIN_ENDPOINT="/users/login";
	protected final static String CONTACTS_ENDPOINT="/contacts";
	protected final static String REQRES_USERS_ENDPOINT="/api/users";
	protected final static String HEROKUAPP_ENDPOINT ="/basic_auth";
	protected final static String FAKETOSTORE_PRODUCTS_ENDPOINT="/products";
	protected final static String OAUTH2_AMADEUS_ACCESSTOKEN_ENDPOINT="/v1/security/oauth2/token";
	protected final static String AMADEUS_FLIGHT_DESTINATIONS_ENDPOINT="/v1/shopping/flight-destinations";
	protected final static String XML_PATHTESTS_ENDPOINT="/xml/books.xml";


@BeforeTest
public void setup() 
{
	restclient=new RestClient();
}

//@After

}
