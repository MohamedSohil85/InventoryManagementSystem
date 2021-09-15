package com.mohamed.inventorymanagementsystem.dao;

import com.mohamed.inventorymanagementsystem.dto.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
    public Category getCategoryByCategoryName(String name);
}
