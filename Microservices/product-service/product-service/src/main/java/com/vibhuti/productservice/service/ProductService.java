package com.vibhuti.productservice.service;

import java.util.List;

import com.vibhuti.productservice.dto.ProductRequest;
import com.vibhuti.productservice.dto.ProductResponse;
import com.vibhuti.productservice.exception.NoProductsFoundException;

public interface ProductService {
	void createProduct(ProductRequest productRequest);

	List<ProductResponse> fetchAllProducts() throws NoProductsFoundException;

}
