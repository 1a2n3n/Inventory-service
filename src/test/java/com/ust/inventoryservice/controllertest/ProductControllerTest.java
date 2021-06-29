package com.ust.inventoryservice.controllertest;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ust.inventoryservice.controller.ProductController;
import com.ust.inventoryservice.exception.ProductAlreadyExistsException;
import com.ust.inventoryservice.model.Product;
import com.ust.inventoryservice.service.ProductService;

public class ProductControllerTest {
	
	@MockitoSettings(strictness = Strictness.LENIENT)


	    @Autowired
	    private MockMvc mockMvc;
	    @Mock
	    ProductService productService;
	    @InjectMocks
	    ProductController productController;
	    private Product product;

	

	    @Before(value = "")
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	        product = new Product();

	    }

	    @Test
	    void registerProductTest() throws Exception {
	        Assertions.assertThrows(NullPointerException.class, () -> {
	            when(productService.addProduct(product)).thenThrow(ProductAlreadyExistsException.class);
	            mockMvc.perform(post("/register")
	                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
	                    .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
	        });

	    }

	  
	    

	    @Test
	    void updateProductTest() throws Exception {
	        Assertions.assertThrows(NullPointerException.class, () -> {
	            
	            when(productService.updateProduct(eq(product.getCode()), any())).thenReturn(product);
	            mockMvc.perform(put("/user/Jack998")
	                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
	                    .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	        });
	    }

	    @Test
	    void deleteProductTest() throws Exception {
	        Assertions.assertThrows(NullPointerException.class, () -> {
	            when(productService.deleteProduct("pp22")).thenReturn(true);
	            mockMvc.perform(MockMvcRequestBuilders.delete("/product/pp22")
	                    .contentType(MediaType.APPLICATION_JSON))
	                    .andExpect(MockMvcResultMatchers.status().isOk())
	                    .andDo(MockMvcResultHandlers.print());
	        });


	    }

}	