package com.example.nobsv2;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.GetProductsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class GetProductsServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductsService getProductsService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_get_products_return_product_dto() {
        //Given
        Product product = new Product();
        product.setId(1);
        product.setName("Test Product");
        product.setDescription("Test Description for Test Product");
        product.setPrice(45.0);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Test Product 2");
        product2.setDescription("Test Description for Test Product 2");
        product2.setPrice(645.0);

        List<Product> products = Arrays.asList(product, product2);
        //Mocks the findAll method to return what we want
        when(productRepository.findAll()).thenReturn(products);
        //Creates our expectedResponse with the correct type
        List<ProductDTO> expectedDTOs = products.stream()
                .map(ProductDTO::new)
                .toList();
        ResponseEntity<List<ProductDTO>> expectedResponse = ResponseEntity.ok(expectedDTOs);
        //When
        ResponseEntity<List<ProductDTO>> response = getProductsService.execute(null);

        //Then
        Assertions.assertEquals(expectedResponse, response);
        verify(productRepository, times(1)).findAll();
    }
}
