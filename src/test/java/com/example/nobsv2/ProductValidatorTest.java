package com.example.nobsv2;

import com.example.nobsv2.exceptions.ErrorMessages;
import com.example.nobsv2.exceptions.ProductNotValidException;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.validators.ProductValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductValidatorTest {
    @Test
    public void given_valid_product_when_execute_then_no_exception() {
        Product validProduct = new Product();
        validProduct.setName("Valid Product");
        validProduct.setDescription("This description is valid enough");
        validProduct.setPrice(21.0);

        //When & Then
        Assertions.assertDoesNotThrow(() -> ProductValidator.execute(validProduct));
    }

    @Test
    public void given_product_with_empty_name_when_execute_then_throw_exception() {
        //Given
        Product productWithEmptyName = new Product();
        productWithEmptyName.setName("");
        productWithEmptyName.setDescription("This description is valid enough");
        productWithEmptyName.setPrice(21.0);

        //When & Then
        ProductNotValidException exception = Assertions.assertThrows(
                ProductNotValidException.class,
                () -> ProductValidator.execute(productWithEmptyName)
        );
        Assertions.assertEquals(ErrorMessages.NAME_REQUIRED.getMessage(), exception.getMessage());
    }

    @Test
    public void given_product_with_short_description_when_execute_then_throw_exception() {
        //Given
        Product productWithShortDescription = new Product();
        productWithShortDescription.setName("Valid Product");
        productWithShortDescription.setDescription("Too short");
        productWithShortDescription.setPrice(21.0);

        //When & Then
        ProductNotValidException exception = Assertions.assertThrows(
                ProductNotValidException.class,
                () -> ProductValidator.execute(productWithShortDescription)
        );
        Assertions.assertEquals(ErrorMessages.DESCRIPTION_LENGTH.getMessage(), exception.getMessage());
    }

    @Test
    public void give_product_with_null_price_when_execute_then_throw_exception() {
        //Given
        Product productWithNullPrice = new Product();
        productWithNullPrice.setName("Valid Product");
        productWithNullPrice.setDescription("This description is valid enough");
        productWithNullPrice.setPrice(null);

        //When & Then
        ProductNotValidException exception = Assertions.assertThrows(
                ProductNotValidException.class,
                () -> ProductValidator.execute(productWithNullPrice)
        );
        Assertions.assertEquals(ErrorMessages.PRICE_CANNOT_BE_NEGATIVE.getMessage(), exception.getMessage());
    }

    @Test
    public void given_product_with_negative_price_when_execute_then_throw_exception() {
        //Given
        Product productWithNegativePrice = new Product();
        productWithNegativePrice.setName("Valid Product");
        productWithNegativePrice.setDescription("This description is valid enough");
        productWithNegativePrice.setPrice(-21.0);

        //When & Then
        ProductNotValidException exception = Assertions.assertThrows(
                ProductNotValidException.class,
                () -> ProductValidator.execute(productWithNegativePrice)
        );
        Assertions.assertEquals(ErrorMessages.PRICE_CANNOT_BE_NEGATIVE.getMessage(), exception.getMessage());
    }
}
