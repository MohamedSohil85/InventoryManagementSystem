package com.mohamed.inventorymanagementsystem.resource;

import com.mohamed.inventorymanagementsystem.dao.BrandRepository;
import com.mohamed.inventorymanagementsystem.dao.ProductRepository;
import com.mohamed.inventorymanagementsystem.dto.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BrandResource {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    public BrandResource(BrandRepository brandRepository, ProductRepository productRepository) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }
    @PostMapping(value = "/Brand",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addBrand(@Valid @RequestBody Brand brand){
        List<Brand>brands= brandRepository.findAll();
        for(Brand obj:brands){
           if (obj.getBrandName().equals(brand.getBrandName())){
               return new ResponseEntity(HttpStatus.FOUND);
           }
        }
        return new ResponseEntity(brandRepository.save(brand),HttpStatus.CREATED);
    }
}
