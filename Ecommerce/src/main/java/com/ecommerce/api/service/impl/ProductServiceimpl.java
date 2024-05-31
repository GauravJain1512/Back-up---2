package com.ecommerce.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.api.entity.Category;
import com.ecommerce.api.entity.Product;
import com.ecommerce.api.entity.User;
import com.ecommerce.api.exception.ResourceNotFoundException;
import com.ecommerce.api.models.ProductDto;
import com.ecommerce.api.models.Ratings;
import com.ecommerce.api.repository.CategoryRepository;
import com.ecommerce.api.repository.ProductRepository;
import com.ecommerce.api.repository.UserRepository;
import com.ecommerce.api.service.ProductService;


@Service
public class ProductServiceimpl implements ProductService {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	
	@Override
	public ProductDto addProduct(Long userId, Integer categoryId, ProductDto productDto) {
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", new Long(categoryId)));
		
		Product product = mapper.map(productDto, Product.class);
		product.setSeller(user);
		product.setCategory(category);
		
		Product savedProduct = productRepository.save(product);
		
		return mapper.map(savedProduct, ProductDto.class);
	}


	
	
	@SuppressWarnings("removal")
	@Override
	public ProductDto getProductById(Integer productId) {
		String productIdInt = String.valueOf(productId);
		
		Product product = this.productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product","productId", new Long(productIdInt)));
		
		return mapper.map(product, ProductDto.class);
	}




	@Override
	public String deleteProductById(Integer productId) {
		String productIdInt = String.valueOf(productId);
		
		Product product = this.productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product","productId", new Long(productIdInt)));
		product.setSeller(null);
		productRepository.delete(product);
		
		return "product delete successfully";
		
	}




	@Override
	public ProductDto updateProduct(Integer productId, ProductDto productDto) {
		String productIdInt = String.valueOf(productId);
		
		Product product = this.productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product","productId", new Long(productIdInt)));
		
		product.setPrice(productDto.getPrice());
		product.setProductName(productDto.getProductName());
		
		Product savedProduct = productRepository.save(product);
		
		return mapper.map(savedProduct, ProductDto.class);
	}




	@Override
	public List<ProductDto> getAllProducts(User user) {
		List<Product> products = productRepository.findBySeller(user);
//		List<ProductDto> list = products.stream().map(product-> mapper.map(products, ProductDto.class)).collect(Collectors.toList());
		List<ProductDto> list = new ArrayList<>();
		ProductDto productDto = null;
		for(Product product : products) {
			productDto = new ProductDto();
			productDto.setPrice(product.getPrice());
			productDto.setProductName(product.getProductName());
			productDto.setProductId(product.getProductId());
			list.add(productDto);
		}
		return list;
	}
	

}
