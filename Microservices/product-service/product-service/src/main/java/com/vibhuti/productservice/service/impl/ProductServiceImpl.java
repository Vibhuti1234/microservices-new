package com.vibhuti.productservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibhuti.productservice.dto.ProductRequest;
import com.vibhuti.productservice.dto.ProductResponse;
import com.vibhuti.productservice.exception.NoProductsFoundException;
import com.vibhuti.productservice.model.Product;
import com.vibhuti.productservice.repository.ProductRepository;
import com.vibhuti.productservice.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription())
				.price(productRequest.getPrice()).build();
		productRepository.save(product);
		log.info("Product {} is saved", product.getId());

	}

	@Override
	public List<ProductResponse> fetchAllProducts() throws NoProductsFoundException {
		List<Product> list = productRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new NoProductsFoundException("No Products Found");
		}
		List<ProductResponse> products = list.stream().map(product -> mapToProductResponse(product))
				.collect(Collectors.toList());
		return products;
	}

	private ProductResponse mapToProductResponse(Product product) {
		ProductResponse productResponse = ProductResponse.builder().id(product.getId()).name(product.getName())
				.description(product.getDescription()).price(product.getPrice()).build();
		return productResponse;
	}

}
