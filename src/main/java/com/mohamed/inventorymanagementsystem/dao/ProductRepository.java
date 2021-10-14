package com.mohamed.inventorymanagementsystem.dao;

import com.mohamed.inventorymanagementsystem.dto.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product>findProductsByProductNameIsStartingWith(String keyword);
    Optional<Product>findByProductName(String name);
    List<Product>findProductsByDateOfExpiryIsBefore(Date date);
    Page<Product> findProductsByCategory_CategoryName(String name , Pageable pageable);
}
