package com.cybage.controller;
import com.cybage.dto.categoryDTO;
import com.cybage.dto.productDTO;
import com.cybage.model.Category;
import com.cybage.model.Product;
import com.cybage.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService iProductService;

    @PostMapping("/addProduct/{id}")
    public ResponseEntity<productDTO> addProduct(@RequestBody productDTO product,@PathVariable("id") categoryDTO category) throws Exception {
        Product entity=productDTO.toEntity(product);
        Category category_id=categoryDTO.toEntity(category);
        entity=iProductService.addProduct(entity,category_id);
        productDTO dto= productDTO.toDTO(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/getProduct")
    public ResponseEntity<List<Product>>getProduct(){
        List<Product> productList=iProductService.getProduct();
     //   List<productDTO> dto=productDTO.toDTO(productList);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PutMapping("updateProduct/{id}")
    public ResponseEntity<?>updateById(@PathVariable("id") int id, @RequestBody productDTO productdto) throws Exception {
        productdto.setProductId(id)
        ;
        Product producto=iProductService.updateProduct(productDTO.toEntity(productdto));
        if(producto!=null){
            return new ResponseEntity<>(producto,HttpStatus.CREATED);
        }
        throw new Exception("Category Update failed");
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?>deleteById(@PathVariable("id") int id) throws Exception {
        int count = iProductService.deleteProduct(id)
                ;
        if (count == 0)
            return new ResponseEntity<>("Product not found", HttpStatus.CREATED);
        return new ResponseEntity<>("Product Deleted Successfully",HttpStatus.OK);
    }


}