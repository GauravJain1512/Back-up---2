package com.ecommerce.api.service;

import java.util.List;

import com.ecommerce.api.entity.User;
import com.ecommerce.api.models.ProductDto;

public interface ProductService {

	ProductDto addProduct(Long userId, Integer categoryId, ProductDto productDto);
	
	ProductDto getProductById(Integer productId);
	
	String deleteProductById(Integer productId);
	
	ProductDto updateProduct(Integer productId, ProductDto productDto);
	
	List<ProductDto> getAllProducts(User user);
}
