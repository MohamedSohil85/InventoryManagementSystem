package com.mohamed.inventorymanagementsystem.dao;

import com.mohamed.inventorymanagementsystem.dto.SalesItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesItemrepository extends JpaRepository<SalesItem,Long> {
}
