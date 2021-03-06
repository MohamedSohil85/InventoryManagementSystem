package com.mohamed.inventorymanagementsystem.resource;

import com.mohamed.inventorymanagementsystem.dao.CategoryRepository;
import com.mohamed.inventorymanagementsystem.dao.ProductRepository;
import com.mohamed.inventorymanagementsystem.dto.Product;
import com.mohamed.inventorymanagementsystem.enums.ProductStatus;
import com.mohamed.inventorymanagementsystem.exception.ResourceNotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductResource {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductResource(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping(value = "/product/{categoryId}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product saveNewProduct(@PathVariable("categoryId")Long id, @RequestBody Product product)throws ResourceNotFound{
        return categoryRepository.findById(id).map(category -> {
            product.setCategory(category);
            product.setAddedOn(new Date());
            LocalDate localDate=LocalDate.now();
            if(product.getDateOfExpiry().equals(localDate)){
                product.setProductStatus(ProductStatus.EXPIRED);
            }
            product.setProductStatus(ProductStatus.SAVED);
            category.getProducts().add(product);
            return productRepository.save(product);
        }).orElseThrow(()->new ResourceNotFound("no category with Id: "+id));
    }
    @GetMapping(value = "/products/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product findProductById(@PathVariable("id")Long id)throws ResourceNotFound{
        return productRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFound("Product with Id :"+id+" not found"));
    }
    @GetMapping(value = "/products/{keyword}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public List findProductsByKeyword(@PathVariable("keyword")String keyword)throws ResourceNotFound{
        List products=productRepository.findAll();
        if (products.isEmpty()){
            throw new ResourceNotFound("List is empty !");
        }
       return productRepository.findProductsByProductNameIsStartingWith(keyword);
    }
    @GetMapping(value = "/products",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(defaultValue = "0")Integer pageNo
            ,@RequestParam(defaultValue = "10")Integer pageSize
            ,@RequestParam("productName")String sortBy){
        Pageable pageable= PageRequest.of(pageNo,pageSize, Sort.by(sortBy).ascending());
        Page<Product>products=productRepository.findAll(pageable);
        if (!products.hasContent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products.getContent(), HttpStatus.OK);
    }
    @GetMapping(value = "/products",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductsByCategoryName(@RequestParam(defaultValue = "0")Integer pageNo,
                                                      @RequestParam(defaultValue ="10")Integer pageSize,
                                                    @RequestParam("productName")String sortBy,
                                                    @RequestParam("categoryName")String categoryName){
        Pageable pageable=PageRequest.of(pageNo,pageSize,Sort.by(sortBy).ascending());
        Page<Product>products=productRepository.findProductsByCategory_CategoryName(categoryName,pageable);
        if (!products.hasContent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products.getContent(),HttpStatus.OK);
    }
    @GetMapping(value = "/product/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product>getProductByName(@PathVariable("name")String name){
        return productRepository.findByProductName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping(value = "/edit/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product>updateProduct(@PathVariable("id")Long id,@RequestBody Product newProduct){
        return productRepository.findById(id)
                                .map(product -> {
                                    product.setProductName(newProduct.getProductName());
                                    product.setPrice(product.getPrice());
                                    product.setProductUnit(newProduct.getProductUnit());
                                    product.setQuantity(newProduct.getQuantity());
                                    product.setProductDesc(newProduct.getProductDesc());
                                    return productRepository.save(product);
                                }).map(ResponseEntity::ok)
                                  .orElse(ResponseEntity.notFound().build());
    }
   @DeleteMapping(value = "/remove/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeById(@PathVariable("id")Long id){
        if(productRepository.existsById(id)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
   }

}
