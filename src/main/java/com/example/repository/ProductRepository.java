package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.ProductModel;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel, String> {
	
	   @Query("{'$and': [{'price': {'$gte': ?0, '$lte': ?1}}, {'name': {'$regex': ?2, '$options': 'i'}}]}")
	    List<ProductModel> findByPriceBetweenAndNameLikeIgnoreCase(int priceFrom, int priceTo, String name, Sort sort);

	    @Query("{'price': {'$gte': ?0, '$lte': ?1}}")
	    List<ProductModel> findByPriceBetween(int from, int to);

	    @Query("{'name': {'$regex': ?0, '$options': 'i'}}")
	    List<ProductModel> findByNameLikeIgnoreCase(String name);

	    @Query("{'$and': [{'price': {'$gte': ?0, '$lte': ?1}}, {'name': {'$regex': ?2, '$options': 'i'}}]}")
	    List<ProductModel> findByPriceBetweenAndNameLikeIgnoreCase(int priceFrom, int priceTo, String name);

	    @Query(value = "{'_id': {'$in': ?0}}", count = true)
	    int countByIdIn(List<String> ids);

	    @Query(value = "{'_id': {'$in': ?0}}", exists = true)
	    boolean existsByIdIn(List<String> ids);

	    @Query(delete = true)
	    void deleteByIdIn(List<String> ids);

	    @Query(sort = "{'name': 1, 'price': -1}")
	    List<ProductModel> findByIdInOrderByNameAscPriceDesc(List<String> ids);
}
