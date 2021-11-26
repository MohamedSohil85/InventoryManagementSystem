package com.mohamed.inventorymanagementsystem.resource;

import com.mohamed.inventorymanagementsystem.dao.OrderItemrepository;
import com.mohamed.inventorymanagementsystem.dao.OrderRepository;
import com.mohamed.inventorymanagementsystem.dao.UserRepository;
import com.mohamed.inventorymanagementsystem.dto.Orders;
import com.mohamed.inventorymanagementsystem.exception.ResourceNotFound;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class OrdersResource {

    final OrderRepository orderRepository;
    final OrderItemrepository orderItemrepository;
    final UserRepository userRepository;

    public OrdersResource(OrderRepository orderRepository, OrderItemrepository orderItemrepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderItemrepository = orderItemrepository;
        this.userRepository = userRepository;
    }





}
