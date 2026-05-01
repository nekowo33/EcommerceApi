package com.ws101.novio.EcommerceApi.repository;

import com.ws101.novio.EcommerceApi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Method Naming
    List<Product> findByCategoryName(String name);

    // Method Naming
    List<Product> findByNameContainingIgnoreCase(String name);

    // JPQL Query
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List<Product> findProductsByPriceRange(@Param("min") double min, @Param("max") double max);
}
