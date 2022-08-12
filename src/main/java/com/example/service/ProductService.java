package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.converter.ProductConverter;
import com.example.exception.NotFoundException;
import com.example.model.ProductModel;
import com.example.model.ProductRequest;
import com.example.model.ProductResponse;
import com.example.parameter.ProductQueryParameter;
import com.example.repository.ProductRepository;
import org.springframework.data.domain.Sort;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

//    public ProductService(ProductRepository repository) {
//        this.productRepository = repository;
//    }    
    
    
    public ProductModel getProduct(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find product."));
    }

    public ProductResponse getProductResponse(String id) {
        ProductModel product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find product."));
        return ProductConverter.toProductResponse(product);
    }
    
//    public ProductModel createProduct(ProductRequest request) {
//    	ProductModel product = ProductConverter.toProduct(request) ;
//        product.setName(request.getName());
//        product.setPrice(request.getPrice());
//
//        return productRepository.insert(product);
//    }

    public ProductResponse createProduct(ProductRequest request) {
        ProductModel product = new ProductModel();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product = productRepository.insert(product);

        return ProductConverter.toProductResponse(product);
    }
    
//    public ProductModel replaceProduct(String id, ProductRequest request) {
//    	ProductModel oldProduct = getProduct(id);
//
//    	ProductModel product = ProductConverter.toProduct(request);
//        product.setId(oldProduct.getId());
//        product.setName(request.getName());
//        product.setPrice(request.getPrice());
//
//        return productRepository.save(product);
//    }

    public ProductResponse replaceProduct(String id, ProductRequest request) {
        ProductModel oldProduct = getProduct(id);
        ProductModel newProduct = ProductConverter.toProduct(request);
        newProduct.setId(oldProduct.getId());

        productRepository.save(newProduct);

        return ProductConverter.toProductResponse(newProduct);
    }
    
    public void deleteProduct(String id) {
    	productRepository.deleteById(id);
    }

//    public List<ProductModel> getProducts(ProductQueryParameter param) {
//        String keyword = Optional.ofNullable(param.getKeyword()).orElse("");
//        int priceFrom = Optional.ofNullable(param.getPriceFrom()).orElse(0);
//        int priceTo = Optional.ofNullable(param.getPriceTo()).orElse(Integer.MAX_VALUE);
//
//        Sort sort = genSortingStrategy(param.getOrderBy(), param.getSortRule());
//
//        return productRepository.findByPriceBetweenAndNameLikeIgnoreCase(priceFrom, priceTo, keyword, sort);
//    }
    
    public List<ProductResponse> getProductResponses(ProductQueryParameter param) {
        String nameKeyword = Optional.ofNullable(param.getKeyword()).orElse("");
        int priceFrom = Optional.ofNullable(param.getPriceFrom()).orElse(0);
        int priceTo = Optional.ofNullable(param.getPriceTo()).orElse(Integer.MAX_VALUE);
        Sort sort = genSortingStrategy(param.getOrderBy(), param.getSortRule());

        List<ProductModel> products = productRepository.findByPriceBetweenAndNameLikeIgnoreCase(priceFrom, priceTo, nameKeyword, sort);

        return products.stream()
                .map(ProductConverter::toProductResponse)
                .collect(Collectors.toList());
    }

    private Sort genSortingStrategy(String orderBy, String sortRule) {
        Sort sort = Sort.unsorted();
        if (Objects.nonNull(orderBy) && Objects.nonNull(sortRule)) {
            Sort.Direction direction = Sort.Direction.fromString(sortRule);
            sort = Sort.by(direction, orderBy);
        }

        return sort;
    }    
}
