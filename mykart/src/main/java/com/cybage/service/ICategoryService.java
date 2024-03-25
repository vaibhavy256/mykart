package com.cybage.service;

import com.cybage.model.Category;

import java.util.List;


public interface ICategoryService {

    public Category addCategory (Category category);

    public List<Category> getCategories ();

    public int deleteCategory(int id ) throws Exception;

    public Category updateCategory(Category category) throws Exception;


}
