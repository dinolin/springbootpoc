package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aop.ActionType;
import com.example.aop.EntityType;
import com.example.aop.SendEmail;
import com.example.auth.UserIdentity;
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

//@Service
public class ProductService {

    private ProductRepository repository;
    private MailService mailService;
    private UserIdentity userIdentity;
    
//   @Autowired
//    private ProductRepository productRepository;
    public ProductService(ProductRepository repository, MailService mailService, UserIdentity userIdentity) {
    	this.repository = repository;
    	this.mailService = mailService;
    	this.userIdentity = userIdentity;
    }
    
    public ProductModel getProduct(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find product."));
    }
    
    public ProductResponse getProductResponse(String id) {
        ProductModel product = repository.findById(id)
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

    @SendEmail(entity = EntityType.PRODUCT, action = ActionType.CREATE)
    public ProductResponse createProduct(ProductRequest request) {
        ProductModel product = new ProductModel();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product = repository.insert(product);

        mailService.sendNewProductMail(product.getId());
        
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

    @SendEmail(entity = EntityType.PRODUCT, action = ActionType.UPDATE, idParamIndex = 0)
    public ProductResponse replaceProduct(String id, ProductRequest request) {
        ProductModel oldProduct = getProduct(id);
        ProductModel newProduct = ProductConverter.toProduct(request);
        newProduct.setId(oldProduct.getId());

        repository.save(newProduct);

        return ProductConverter.toProductResponse(newProduct);
    }
    
    @SendEmail(entity = EntityType.PRODUCT, action = ActionType.DELETE, idParamIndex = 0)
    public void deleteProduct(String id) {
    	repository.deleteById(id);
    	mailService.sendDeleteProductMail(id);
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

        List<ProductModel> products = repository.findByPriceBetweenAndNameLikeIgnoreCase(priceFrom, priceTo, nameKeyword, sort);
        System.out.print(products.toString());
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
