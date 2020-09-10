package com.home.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.home.app.SpringBootTestingConfigrationDemo;
import com.home.app.dao.ProductDao;
import com.home.app.model.Product;
import com.home.app.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootTestingConfigrationDemo.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;
	private HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void testSaveProduct() throws Exception {
		Product product = new Product();
		product.setId(1);
		product.setDescription("Samsung Galaxy J7 SM-J700F");
		product.setPrice(new BigDecimal("13299.00"));
		product.setImageUrl("https://www.amazon.in/Samsung-Galaxy-J7-SM-J700F-Gold/dp/B014DYVWWS?tag=googinhydr18418-21&tag=googinkenshoo-21&ascsubtag=115b9bbe-7ef0-4c67-825a-60b83fba9303");
		product.setProductId("23526889931068308");
		String inputInJson = this.mapToJson(product);
		String URI = "/product/add";
		HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);
		ResponseEntity<String> response = testRestTemplate.exchange(
				formFullURLWithPort(URI),
				HttpMethod.POST, entity, String.class);
		inputInJson="Product saved successfully";
		String responseInJson = response.getBody();
		assertThat(responseInJson).isEqualTo(inputInJson);
	}
	
	@Test
	public void getProductById() throws Exception {
		Product product = new Product();
		product.setId(1);
		product.setDescription("Samsung Galaxy J7 SM-J700F");
		product.setPrice(new BigDecimal("13299.00"));
		product.setImageUrl("https://www.amazon.in/Samsung-Galaxy-J7-SM-J700F-Gold/dp/B014DYVWWS?tag=googinhydr18418-21&tag=googinkenshoo-21&ascsubtag=115b9bbe-7ef0-4c67-825a-60b83fba9303");
		product.setProductId("23526889931068308");
		product.setVersion(0);
		String inputInJson = this.mapToJson(product);
		System.out.println("inputInJson: "+inputInJson);
		String URI = "/product/add";
		HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);
		ResponseEntity<String> response = testRestTemplate.exchange(
				formFullURLWithPort(URI),
				HttpMethod.POST, entity, String.class);
		String getURI = "/product/show/1";
        Product bodyJsonResponse = testRestTemplate.getForObject(formFullURLWithPort(getURI), Product.class);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(bodyJsonResponse);
        System.out.println("json: "+json);
		assertThat(json).isEqualTo(inputInJson);
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	private String formFullURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
