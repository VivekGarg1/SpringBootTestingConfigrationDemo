package com.home.app.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.home.app.model.Product;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductDaoTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ProductDao productDao;
	
	@Test
	public void testSaveProduct() {
		Product samsungGalaxy = new Product();
		samsungGalaxy.setDescription("Samsung Galaxy J7 SM-J700F");
		samsungGalaxy.setPrice(new BigDecimal("13299.00"));
		samsungGalaxy.setImageUrl("https://www.amazon.in/Samsung-Galaxy-J7-SM-J700F-Gold/dp/B014DYVWWS?tag=googinhydr18418-21&tag=googinkenshoo-21&ascsubtag=115b9bbe-7ef0-4c67-825a-60b83fba9303");
		samsungGalaxy.setProductId("23526889931068308");
		Product saveInDB = entityManager.persist(samsungGalaxy);
		Product getFeomDB = productDao.findById(saveInDB.getId()).orElse(null);
		assertThat(getFeomDB).isEqualTo(saveInDB);
	}
	
	@Test
	public void getProductById() {
		Product iPhone = new Product();
		iPhone.setDescription("Apple iPhone 7");
		iPhone.setImageUrl("https://www.flipkart.com/apple-iphone-7-rose-gold-32-gb/p/itmen6dapsvxanrk?gclid=Cj0KCQjwgIPOBRDnARIsAHA1X3Rm2-sqRfpmiyWQn3B246U8_MYk0eTO3UgZ0TiR2y1Wh-IqJMZ5EBIaAgIUEALw_wcB&pid=MOBEMK62JSRHU85T&lid=LSTMOBEMK62JSRHU85TIU9DKW&cmpid=content_mobile_8965229628_gmc_pla&tgi=sem%2C1%2CG%2C11214002%2Cg%2Csearch%2C%2C50314728260%2C1o1%2C%2C%2Cc%2C%2C%2C%2C%2C%2C%2C&s_kwcid=AL%21739%213%2150314728260%21%21%21%21356518884166%21&ef_id=Wb0-8gAABBk1ZGmB%3A20170920024538%3As");
		iPhone.setProductId("198639393495300006");
		iPhone.setPrice(new BigDecimal("47499"));
		Product saveInDB = entityManager.persist(iPhone);
		//get ticket from DB
		Product getFeomDB = productDao.findById(saveInDB.getId()).orElse(null);
		assertThat(getFeomDB).isEqualTo(saveInDB);
	}
	
	@Test
	public void getAllProductById() {
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
		entityManager.persist(samsungGalaxy);
		entityManager.persist(iPhone);
		
		List<Product> products = productDao.findAll();
		assertThat(products.size()).isEqualTo(2);
	}
	
	@Test
	public void deleteProductById() {
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
		Product product = entityManager.persist(samsungGalaxy);
		entityManager.persist(iPhone);
		entityManager.remove(product);
		
		List<Product> products = productDao.findAll();
		assertThat(products.size()).isEqualTo(1);
	}
	
	@Test
	public void updateProductById() {
		Product iPhone = new Product();
		iPhone.setDescription("Apple iPhone 7");
		iPhone.setImageUrl("https://www.flipkart.com/apple-iphone-7-rose-gold-32-gb/p/itmen6dapsvxanrk?gclid=Cj0KCQjwgIPOBRDnARIsAHA1X3Rm2-sqRfpmiyWQn3B246U8_MYk0eTO3UgZ0TiR2y1Wh-IqJMZ5EBIaAgIUEALw_wcB&pid=MOBEMK62JSRHU85T&lid=LSTMOBEMK62JSRHU85TIU9DKW&cmpid=content_mobile_8965229628_gmc_pla&tgi=sem%2C1%2CG%2C11214002%2Cg%2Csearch%2C%2C50314728260%2C1o1%2C%2C%2Cc%2C%2C%2C%2C%2C%2C%2C&s_kwcid=AL%21739%213%2150314728260%21%21%21%21356518884166%21&ef_id=Wb0-8gAABBk1ZGmB%3A20170920024538%3As");
		iPhone.setProductId("198639393495300006");
		iPhone.setPrice(new BigDecimal("47499"));
		Product saveInDB = entityManager.persist(iPhone);
		//get ticket from DB
		Product getFeomDB = productDao.findById(saveInDB.getId()).orElse(null);
		getFeomDB.setPrice(new BigDecimal("55000"));
		entityManager.persist(getFeomDB);
		assertThat(getFeomDB.getPrice()).isEqualTo("55000");
	}
}
