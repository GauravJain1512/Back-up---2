package com.ecommerce.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.api.entity.Category;
import com.ecommerce.api.entity.Product;
import com.ecommerce.api.entity.User;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Optional<Product> findBySellerAndCategory(User user, Category category);
	
	List<Product> findBySeller(User seller);
}
