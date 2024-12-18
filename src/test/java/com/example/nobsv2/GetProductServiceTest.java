package com.example.nobsv2;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.GetProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class GetProductServiceTest {

    @Mock //Mock the response of something
    private ProductRepository productRepository;

    @InjectMocks //The class I'm testing
    private GetProductService getProductService;

    @BeforeEach //Things required before the test runs
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_get_product_service_return_product_dto() {
        //Given
        Product product = new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription("Product Description that needs to be at least 15 chars");
        product.setPrice(15.0);

        //still part of the given
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        //When
        ResponseEntity<ProductDTO> response = getProductService.execute(1);

        //Then
        Assertions.assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);
        //asserts the product repository was only called once
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void given_product_does_not_exist_when_get_product_service_throw_product_not_found_exception() {
        //Given
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        //When & Then
        Assertions.assertThrows(ProductNotFoundException.class, () -> getProductService.execute(1));
        verify(productRepository, times(1)).findById(1);
    }
}

