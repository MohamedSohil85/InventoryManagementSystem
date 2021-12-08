package com.mohamed.inventorymanagementsystem.resource;

import com.mohamed.inventorymanagementsystem.dao.SalesItemrepository;
import com.mohamed.inventorymanagementsystem.dao.SalesRepository;
import com.mohamed.inventorymanagementsystem.dao.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SalessResource {

    final SalesRepository SalesRepository;
    final SalesItemrepository SalesItemrepository;
    final UserRepository userRepository;

    public SalessResource(SalesRepository SalesRepository, SalesItemrepository SalesItemrepository, UserRepository userRepository) {
        this.SalesRepository = SalesRepository;
        this.SalesItemrepository = SalesItemrepository;
        this.userRepository = userRepository;
    }





}
