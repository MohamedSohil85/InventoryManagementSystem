package com.mohamed.inventorymanagementsystem.dao;

import com.mohamed.inventorymanagementsystem.dto.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {
}
