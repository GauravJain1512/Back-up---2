package com.ecommerce.rating.api.entity;

import javax.persistence.Entity;

import com.ecommerce.api.models.ProductDto;

@Entity
public class Ratings {

private String ratingId;
	
	private String reviewComment;
	
	private Double rating;
	
	private ProductDto productDto;

}
