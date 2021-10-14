package com.mohamed.inventorymanagementsystem.dao;

import com.mohamed.inventorymanagementsystem.dto.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDetailrepository extends JpaRepository<OrderDetail,Long> {
}
