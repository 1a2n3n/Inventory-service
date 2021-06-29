package com.ust.inventoryservice.repositorytest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.ust.inventoryservice.model.Product;
import com.ust.inventoryservice.repository.ProductRepository;


@SpringBootTest
public class ProductRepositoryTest {
	

	    @Autowired
	    private ProductRepository productRepository;

	    Product product = new Product();

	    @Test
	    public void registerProductTest() {
	        productRepository.save(product);
	        Product fetchedProduct = productRepository.findById("pp22").get();
	        Assertions.assertEquals(product.getCode(), fetchedProduct.getCode());

	    }

	    @Test
	    public void deleteProductTest() {
	       
	        productRepository.save(product);
	        Product fetchedproduct = productRepository.findById("pp22").get();
	        Assertions.assertEquals("pp22", fetchedproduct.getCode());
	        productRepository.delete(fetchedproduct);
	        fetchedproduct = productRepository.findById("pp22").get();
	        }

	    

	    @Test
	    public void updateProductTest() {
	        productRepository.save(product);
	        Product fetchedproduct = productRepository.findById("pp22").get();
	        
	        productRepository.save(fetchedproduct);
	      Assertions.assertEquals("pp22", fetchedproduct.getCode());
	    }
}
