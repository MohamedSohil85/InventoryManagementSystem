package com.mohamed.inventorymanagementsystem.resource;

import com.mohamed.inventorymanagementsystem.dao.BrandRepository;
import com.mohamed.inventorymanagementsystem.dao.ProductRepository;
import com.mohamed.inventorymanagementsystem.dto.Brand;
import com.mohamed.inventorymanagementsystem.dto.Product;
import com.mohamed.inventorymanagementsystem.exception.ResourceNotFound;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    @GetMapping(value = "/Brands",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Brand> getBrands()throws ResourceNotFound {
        List<Brand>brands= brandRepository.findAll(Sort.by("brandName").ascending());
        if (brands.isEmpty()){
            throw new ResourceNotFound("List is Empty");
        }
       return brands;
    }
    @GetMapping(value = "/Brand/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Brand> getBrandById(@PathVariable("id")Long id)throws ResourceNotFound{
        return brandRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(()->new ResourceNotFound("Brand with Id :"+id+" not found"));
    }
    @PostMapping(value = "/Brand/{id}/Product/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addProductToBrand(@PathVariable("id")Long productId,@PathVariable("id")Long brandId){
        return brandRepository.findById(brandId).map(brand -> {
            Optional<Product>optionalProduct=productRepository.findById(productId);
            Product product= optionalProduct.get();
            product.setBrand(brand);
            brand.getProducts().add(product);
            return new ResponseEntity<>(brandRepository.save(brand),HttpStatus.CREATED);
        }).orElse(new ResponseEntity(HttpStatus.NO_CONTENT));
    }
}
