package com.example.nobsv2;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.services.DeleteProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class DeleteProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DeleteProductService deleteProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_delete_product_service_return_product_deleted() {
        //Given
        Product product = new Product();
        product.setId(1);
        product.setName("test");
        product.setDescription("Big description for testing here");
        product.setPrice(16.78);
        //Mock for the productRepository to return the mock product above.
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        //When
        deleteProductService.execute(1);

        //Then
        verify(productRepository, times(1)).deleteById(1);
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void given_does_not_exist_when_delete_product_service_return_product_not_found_exception() {
        //Given
        //Mock for the productRepository to return with ProductNotFound exception.
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        //When & Then
        Assertions.assertThrows(ProductNotFoundException.class, () -> deleteProductService.execute(1));
        //Since no product was found, the method deleteById should not be called
        verify(productRepository, never()).deleteById(1);
    }
}
