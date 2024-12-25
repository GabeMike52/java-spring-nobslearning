package com.example.nobsv2.product;

import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.UpdateProductCommand;
import com.example.nobsv2.product.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final CreateProductService createProductService;

    private final GetProductsService getProductsService;

    private final GetProductService getProductService;

    private final UpdateProductService updateProductService;

    private final DeleteProductService deleteProductService;
    private final SearchProductService searchProductService;

    public ProductController(DeleteProductService deleteProductService,
                             UpdateProductService updateProductService,
                             GetProductsService getProductsService,
                             CreateProductService createProductService,
                             GetProductService getProductService,
                             SearchProductService searchProductService) {
        this.deleteProductService = deleteProductService;
        this.updateProductService = updateProductService;
        this.getProductsService = getProductsService;
        this.createProductService = createProductService;
        this.getProductService = getProductService;
        this.searchProductService = searchProductService;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        return createProductService.execute(product);
    }


    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return getProductsService.execute(null);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        return getProductService.execute(id);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam String name) {
        return searchProductService.execute(name);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody Product product) {

        return updateProductService.execute(new UpdateProductCommand(id, product));
    }


    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return deleteProductService.execute(id);
    }

}
