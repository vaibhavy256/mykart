package com.cybage.service;

import com.cybage.model.Category;
import com.cybage.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}
