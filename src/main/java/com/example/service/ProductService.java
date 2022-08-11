package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.exception.NotFoundException;
import com.example.model.ProductModel;
import com.example.parameter.ProductQueryParameter;
import com.example.repository.ProductRepository;
import org.springframework.data.domain.Sort;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductModel getProduct(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find product."));
    }

    public ProductModel createProduct(ProductModel request) {
    	ProductModel product = new ProductModel();
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        return productRepository.insert(product);
    }

    public ProductModel replaceProduct(String id, ProductModel request) {
    	ProductModel oldProduct = getProduct(id);

    	ProductModel product = new ProductModel();
        product.setId(oldProduct.getId());
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
    	productRepository.deleteById(id);
    }

    public List<ProductModel> getProducts(ProductQueryParameter param) {
        String keyword = Optional.ofNullable(param.getKeyword()).orElse("");
        int priceFrom = Optional.ofNullable(param.getPriceFrom()).orElse(0);
        int priceTo = Optional.ofNullable(param.getPriceTo()).orElse(Integer.MAX_VALUE);

        Sort sort = genSortingStrategy(param.getOrderBy(), param.getSortRule());

        return productRepository.findByPriceBetweenAndNameLikeIgnoreCase(priceFrom, priceTo, keyword, sort);
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
