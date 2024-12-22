package com.example.nobsv2.product.services;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.Query;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsService implements Query<Void, List<ProductDTO>> {

    private final ProductRepository productRepository;
    private final static Logger logger = LoggerFactory.getLogger(GetProductsService.class);
    public GetProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {
        logger.info("Executing " + getClass());
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = products.stream().map(ProductDTO::new).toList();
        //No need for exception here, if none found return empty list
        return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
    }
}
