package com.op2.op2.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.op2.op2.domain.Category;
import com.op2.op2.domain.CategoryRepository;

@RestController
@CrossOrigin("http://localhost:5173")
public class CategoryRestController {
    private final Logger log = LoggerFactory.getLogger(CategoryRestController.class);

    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping({ "/categories" })
    public @ResponseBody List<Category> findAllCategories(){
        log.info("Fetch all categories");
        try {
            return (List<Category>) categoryRepo.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No categories found");
        }
    }
/* 
    // get category by name
    @GetMapping({ "/categories/{name}" })
    public @ResponseBody Optional<Category> getCategoryByName(@PathVariable("name") String categoryName){
        log.info("Fetch category by name");
        try{
            return categoryRepo.findByName(categoryName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find a category");
        }       
    }
*/
    @GetMapping("/categories/{name}")
public @ResponseBody Category getCategoryByName(@PathVariable("name") String categoryName) {
    log.info("Fetch category by name");
    try {
        return categoryRepo.findAllByCategoryName(categoryName);
    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find a category");
    }
}
    // save new category
    @PostMapping({ "/categories" })
    public @ResponseBody Category saveCategoryRest(@RequestBody Category category){
        return categoryRepo.save(category);
    }

    
}
