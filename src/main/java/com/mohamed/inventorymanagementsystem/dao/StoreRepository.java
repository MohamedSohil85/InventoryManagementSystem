package com.mohamed.inventorymanagementsystem.dao;

import com.mohamed.inventorymanagementsystem.dto.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    List<Store> findStoresByCategoryAndLocation(String category, String location);
}
