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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.app.dao.ProductDao;
import com.home.app.model.Product;
import com.home.app.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService productService;
	
	@MockBean
	private ProductDao productDao;
	
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
		Mockito.when(productService.saveProduct(Mockito.any(Product.class))).thenReturn(product);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		inputInJson="Product saved successfully";
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void getProductById() throws Exception {
		Product product = new Product();
		product.setId(1);
		product.setDescription("Apple iPhone 7");
		product.setImageUrl("https://www.flipkart.com/apple-iphone-7-rose-gold-32-gb/p/itmen6dapsvxanrk?gclid=Cj0KCQjwgIPOBRDnARIsAHA1X3Rm2-sqRfpmiyWQn3B246U8_MYk0eTO3UgZ0TiR2y1Wh-IqJMZ5EBIaAgIUEALw_wcB&pid=MOBEMK62JSRHU85T&lid=LSTMOBEMK62JSRHU85TIU9DKW&cmpid=content_mobile_8965229628_gmc_pla&tgi=sem%2C1%2CG%2C11214002%2Cg%2Csearch%2C%2C50314728260%2C1o1%2C%2C%2Cc%2C%2C%2C%2C%2C%2C%2C&s_kwcid=AL%21739%213%2150314728260%21%21%21%21356518884166%21&ef_id=Wb0-8gAABBk1ZGmB%3A20170920024538%3As");
		product.setProductId("198639393495300006");
		product.setPrice(new BigDecimal("47499"));
		Mockito.when(productService.getProductById(Mockito.anyInt())).thenReturn(product);
		String URI = "/product/show/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(product);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	public void getAllProductById() throws Exception {
		Product samsungGalaxy = new Product();
		samsungGalaxy.setDescription("Samsung Galaxy J7 SM-J700F");
		samsungGalaxy.setPrice(new BigDecimal("13299.00"));
		samsungGalaxy.setImageUrl("https://www.amazon.in/Samsung-Galaxy-J7-SM-J700F-Gold/dp/B014DYVWWS?tag=googinhydr18418-21&tag=googinkenshoo-21&ascsubtag=115b9bbe-7ef0-4c67-825a-60b83fba9303");
		samsungGalaxy.setProductId("23526889931068308");
		
		Product iPhone = new Product();
		iPhone.setDescription("Apple iPhone 7");
		iPhone.setImageUrl("https://www.flipkart.com/apple-iphone-7-rose-gold-32-gb/p/itmen6dapsvxanrk?gclid=Cj0KCQjwgIPOBRDnARIsAHA1X3Rm2-sqRfpmiyWQn3B246U8_MYk0eTO3UgZ0TiR2y1Wh-IqJMZ5EBIaAgIUEALw_wcB&pid=MOBEMK62JSRHU85T&lid=LSTMOBEMK62JSRHU85TIU9DKW&cmpid=content_mobile_8965229628_gmc_pla&tgi=sem%2C1%2CG%2C11214002%2Cg%2Csearch%2C%2C50314728260%2C1o1%2C%2C%2Cc%2C%2C%2C%2C%2C%2C%2C&s_kwcid=AL%21739%213%2150314728260%21%21%21%21356518884166%21&ef_id=Wb0-8gAABBk1ZGmB%3A20170920024538%3As");
		iPhone.setProductId("198639393495300006");
		iPhone.setPrice(new BigDecimal("47499"));
		List<Product> productList=new ArrayList<Product>();
		productList.add(samsungGalaxy);
		productList.add(iPhone);
		Mockito.when(productService.listAllProducts()).thenReturn(productList);
		String URI = "/product/list";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(productList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
