package com.cybage.service;

import com.cybage.model.Category;
import com.cybage.model.User;
import com.cybage.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    ICategoryRepository categoryRepository;

    public Category addCategory (Category category){
        Category newCategory=categoryRepository.save(category);
        return newCategory;
    }

    public List<Category> getCategories (){
        return categoryRepository.findAll();
    }

    public String deleteCategory(int id ) throws Exception {

        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new Exception("Category does not exists");
        } else {
            categoryRepository.deleteById(id);
            return "Category deleted successfully ";
        }
    }

    public Category updateCategory(Category category) throws Exception {
        Category updateCategory = categoryRepository.findById(category.getCategoryId()).orElse(null);
        if (category == null) {
            throw new Exception("Category does not exists");
        } else {
            updateCategory.setCategoryType(category.getCategoryType());
            updateCategory.setDescription(category.getDescription());
            return categoryRepository.save(updateCategory);
        }
    }
}
