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

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    IProductRepository productRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    ICategoryRepository categoryRepository;

    public Product addProduct(Product product) throws Exception {
        User currentUser = userRepository.findByEmail(product.getUserEmail());
        if (currentUser == null) {
            throw new Exception("User does not exists");
        } else {
            Product newProduct = new Product();
            newProduct.setUser(currentUser);
            newProduct.setUserEmail(product.getUserEmail());
            newProduct.setImageLink(product.getImageLink());
            newProduct.setDescription(product.getDescription());
            newProduct.setPrice(product.getPrice());
            newProduct.setCategory(product.getCategory());
            //newProduct.setCategory(category_id);
            productRepository.save(newProduct);
            return newProduct;
        }
    }

//        public Product addProduct (productDTO product){
//            Product products = new Product();
//            BeanUtils.copyProperties(product, products);
//            Product newProduct=productRepository.save(products);
//            return newProduct;
//        }

    public List<Product> getProduct() {
        return productRepository.findAll();
    }


    public int deleteProduct(int id) throws Exception {
        System.out.println(productRepository.findById(id).orElse(null));
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id)
            ;
            return 1;
        } else {
            return 0;
        }
    }

    public Product updateProduct(Product product) throws Exception {
        Product updateproduct = productRepository.findById(product.getProductId()).orElse(null);
        System.out.println(updateproduct);
        if (updateproduct != null) {
            updateproduct.setPrice(product.getPrice());
            updateproduct.setDescription(product.getDescription());
            return productRepository.save(updateproduct);
        } else {
            throw new Exception("Category does not exists");
        }
    }
}



