package com.hericode.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hericode.product.entity.ProductEntity;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
	

}
