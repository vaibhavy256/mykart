package com.cybage.controller;

import com.cybage.dto.categoryDTO;
import com.cybage.model.Category;
import com.cybage.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<categoryDTO> addCategory(@RequestBody categoryDTO categoryDto){
        Category newCategory=categoryService.addCategory(categoryDTO.toEntity(categoryDto));
        categoryDTO dto=categoryDTO.toDTO(newCategory);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<categoryDTO>>getCategory(){
        List<Category> categories=categoryService.getCategories();
        List<categoryDTO> dto=categoryDTO.toDTO(categories);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("updateCategory/{id}")
    public ResponseEntity<?>updateById(@PathVariable("id") int id, @RequestBody categoryDTO categoryDto) throws Exception {
        categoryDto.setCategoryId(id);
        Category category=categoryService.updateCategory(categoryDTO.toEntity(categoryDto));
        if(category!=null){
            return new ResponseEntity<>(category,HttpStatus.CREATED);
        }
        throw new Exception("Category Update failed");
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?>deleteById(@PathVariable("id") int id) throws Exception {
        int count = categoryService.deleteCategory(id);
        if (count == 0)
            return new ResponseEntity<>("Category not found", HttpStatus.CREATED);
        return new ResponseEntity<>("Category Deleted Successfully",HttpStatus.OK);
    }



}
