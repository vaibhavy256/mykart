package com.cybage.service;

import com.cybage.dto.productDTO;
import com.cybage.model.Category;
import com.cybage.model.Product;

import java.util.List;

public interface IProductService {

    public Product addProduct(Product product, Category category_id) throws Exception;

    public List<Product> getProduct();


    public int deleteProduct(int id)throws Exception;

    public Product updateProduct(Product product) throws Exception;
}
