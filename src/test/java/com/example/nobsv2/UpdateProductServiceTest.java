package com.example.nobsv2;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.UpdateProductCommand;
import com.example.nobsv2.product.services.UpdateProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class UpdateProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private UpdateProductService updateProductService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_update_product_service_return_product_dto() {
        //Given
        Product product = new Product();
        product.setId(1);
        product.setName("test");
        product.setDescription("description big enough to pass");
        product.setPrice(345.45);

        UpdateProductCommand updatedProduct = new UpdateProductCommand(1, product);
        updatedProduct.getProduct().setName("Updated");
        updatedProduct.getProduct().setDescription("Updated for testing now");
        updatedProduct.getProduct().setPrice(54656.232);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        //When
        ResponseEntity<ProductDTO> productDTOResponseEntity = updateProductService.execute(updatedProduct);

        //Then
        Assertions.assertEquals(productDTOResponseEntity.getBody().getName(), product.getName());
        Assertions.assertEquals(productDTOResponseEntity.getBody().getDescription(), product.getDescription());
        Assertions.assertEquals(productDTOResponseEntity.getBody().getPrice(), product.getPrice());
        verify(productRepository, times(1)).findById(1);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void given_product_does_not_exist_when_update_product_service_throw_product_not_found_exception() {
        //Given
        Product product = new Product();
        product.setId(5);
        product.setName("test");
        product.setDescription("description big enough to pass");
        product.setPrice(345.45);

        UpdateProductCommand updatedProduct = new UpdateProductCommand(1, product);
        updatedProduct.getProduct().setName("Updated");
        updatedProduct.getProduct().setDescription("Updated for testing now");
        updatedProduct.getProduct().setPrice(54656.232);
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        //When & Then
        Assertions.assertThrows(ProductNotFoundException.class, () -> updateProductService.execute(updatedProduct));
        verify(productRepository, times(1)).findById(1);
        verify(productRepository, never()).save(product);
    }
}
