package com.cybage.service;

import com.cybage.dto.productDTO;
import com.cybage.model.Category;
import com.cybage.model.Product;
import com.cybage.model.User;
import com.cybage.repository.ICategoryRepository;
import com.cybage.repository.IProductRepository;
import com.cybage.repository.IUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements  IProductService{

    @Autowired
    IProductRepository productRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    ICategoryRepository categoryRepository;

//    public Product addProduct (Product product, Category category) throws Exception {
//     User user =userRepository.findById(product.getUser());
//        if(user==null){
//            throw new Exception("Seller does not exists");
//        }
//        else {
//            Product newProduct = productRepository.save(product);
//            return newProduct;
//        }
//    }

    public Product addProduct(productDTO productDto ){
        Product product=new Product();
        BeanUtils.copyProperties(productDto, product);

        // Get category by ID
        Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
        categoryOptional.ifPresent(product::setCategory);

        // Get seller (user) by ID
        Optional<User> userOptional = userRepository.findById(productDto.getSellerId());
        userOptional.ifPresent(product::setUser);
        return productRepository.save(product);
    }


}
