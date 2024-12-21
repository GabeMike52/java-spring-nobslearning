package com.example.nobsv2;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.SearchProductService;
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

public class SearchProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SearchProductService searchProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_search_product_service_return_product_dto() {
        //Given
        Product product = new Product();
        product.setId(1);
        product.setName("Product Test");
        product.setDescription("testing search product service");
        product.setPrice(1.0);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Fake");
        product2.setDescription("Something here, something there");
        product2.setPrice(12.0);

        List<Product> filteredProducts = List.of(product);

        when(productRepository.findByNameOrDescriptionContaining("product")).thenReturn(filteredProducts);
        List<ProductDTO> expectedDTOs = filteredProducts.stream()
                .map(ProductDTO::new)
                .toList();
        ResponseEntity<List<ProductDTO>> expectedResponse = ResponseEntity.ok(expectedDTOs);

        //When
        ResponseEntity<List<ProductDTO>> response = searchProductService.execute("product");

        //Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expectedResponse.getBody(), response.getBody());
        System.out.println(response.getBody());
        verify(productRepository, times(1)).findByNameOrDescriptionContaining("product");
    }
}
