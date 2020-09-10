package com.home.app.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="product")
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Version
    @Column(name="version")
    private Integer version;
    
    @Column(name="product_id")
    private String productId;
    
    @Column(name="description")
    private String description;
    
    @Column(name="image_url",length=4000)
    private String imageUrl;
    
    @Column(name="price")
    private BigDecimal price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

	@Override
	public String toString() {
		return "Product [id=" + id + ", version=" + version + ", productId=" + productId + ", description="
				+ description + ", imageUrl=" + imageUrl + ", price=" + price + "]";
	}

}
