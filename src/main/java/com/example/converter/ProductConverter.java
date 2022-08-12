package com.example.converter;

import com.example.model.ProductModel;
import com.example.model.ProductRequest;
import com.example.model.ProductResponse;

public class ProductConverter {
	
    private ProductConverter() {

    }

    public static ProductModel toProduct(ProductRequest request) {
        ProductModel product = new ProductModel();
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        return product;
    }
    
    public static ProductResponse toProductResponse(ProductModel product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());

        return response;
    }
}
