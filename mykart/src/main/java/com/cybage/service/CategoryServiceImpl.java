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

    public  int deleteCategory(int id ) throws Exception {
        System.out.println(categoryRepository.findById(id).orElse(null));
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return 1;
        } else {
            return 0;
        }
    }
    public Category updateCategory(Category category) throws Exception {
        Category updatecategory = categoryRepository.findById(category.getCategoryId()).orElse(null);
        System.out.println(updatecategory);
        if (updatecategory != null) {
            updatecategory.setCategoryType(category.getCategoryType());
            updatecategory.setDescription(category.getDescription());
            return categoryRepository.save(updatecategory);
        } else {
            throw new Exception("Category does not exists");
        }
    }
}
