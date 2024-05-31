package com.ecommerce.api.models;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;

import com.ecommerce.api.entity.CartProduct;
import com.ecommerce.api.entity.Category;
import com.ecommerce.api.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
	
	private Integer productId;
	@NotEmpty(message = "Please enter valid product name")
	private String productName;
	@Range(min = 0l, message = "price should be more than zero")
	private Double price;

	private List<Ratings> ratings = new ArrayList<>();
	

}
