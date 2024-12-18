package com.example.nobsv2;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.CreateProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class CreateProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_information_when_create_product_return_product_dto() {
        //Given
        Product product = new Product();
        product.setId(4);
        product.setDescription("description for testing here");
        product.setName("Testing Creation");
        product.setPrice(456.0);

        when(productRepository.save(product)).thenReturn(product);

        //When
        ResponseEntity<ProductDTO> response = createProductService.execute(product);

        //Then
        Assertions.assertEquals(new ProductDTO(product), response.getBody());
        verify(productRepository, times(1)).save(product);
    }
}
