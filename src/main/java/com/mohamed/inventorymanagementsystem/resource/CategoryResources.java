package com.mohamed.inventorymanagementsystem.resource;

import com.mohamed.inventorymanagementsystem.dao.CategoryRepository;
import com.mohamed.inventorymanagementsystem.dto.Category;
import com.mohamed.inventorymanagementsystem.exception.ResourceNotFound;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api")
@RestController
public class CategoryResources {
    private final CategoryRepository categoryRepository;

    public CategoryResources(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping(value = "/Category",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>addNewCategory(@RequestBody final Category category){
        String categoryName= category.getCategoryName();
        Category category1= categoryRepository.getCategoryByCategoryName(categoryName);
        if(category1.equals(category)){
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        return new ResponseEntity<>(categoryRepository.save(category),HttpStatus.CREATED);
    }
    @GetMapping(value = "/categories",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> showCategories() throws ResourceNotFound{
        List<Category>categories= (List<Category>) categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new ResourceNotFound("List is Empty");
        }
        return categories;
    }
    @GetMapping(value = "/category/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category findCategoryById(@PathVariable("id")Long id)throws ResourceNotFound{
        return categoryRepository.findById(id).orElseThrow(()->new ResourceNotFound("no category with id :"+id));
    }
}
