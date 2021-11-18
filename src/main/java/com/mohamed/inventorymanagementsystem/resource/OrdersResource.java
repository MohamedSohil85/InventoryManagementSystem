package com.mohamed.inventorymanagementsystem.resource;

import com.mohamed.inventorymanagementsystem.dao.OrderRepository;
import com.mohamed.inventorymanagementsystem.dao.OrdersDetailrepository;
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
    final OrdersDetailrepository ordersDetailrepository;
    final UserRepository userRepository;

    public OrdersResource(OrderRepository orderRepository, OrdersDetailrepository ordersDetailrepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.ordersDetailrepository = ordersDetailrepository;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/Order/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Orders createOrder(@Valid @RequestBody Orders orders, @PathVariable("userId")Long id)throws ResourceNotFound {
    return userRepository.findById(id).map(user -> {
        user.getOrdersList().add(orders);
        orders.setUser(user);
        orders.setOrderDate(new Date());
        return orderRepository.save(orders);
    }).orElseThrow(()->new ResourceNotFound("User with Id :"+id+" not found"));
    }


}
