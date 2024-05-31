package com.ecommerce.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ratings {
	
	private String ratingId;
	
	private String reviewComment;
	
	private Double rating;
	
	private ProductDto productDto;

}
