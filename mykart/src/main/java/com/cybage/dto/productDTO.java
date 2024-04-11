package com.cybage.dto;

import com.cybage.model.Category;
import com.cybage.model.Product;
import com.cybage.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.boot.archive.scan.spi.ClassDescriptor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class productDTO {
    private int productId;

    private String description;

    private String image_link;

    private double price;

    private Category category;

    private User seller;

    private String userEmail;

    private Date productDate;


    public static Product toEntity(productDTO dto) {
        Product entity = new Product();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static productDTO toDTO(Product entity) {
        productDTO dto = new productDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static List<productDTO> toDTO(List<Product> productList) {

        return productList.stream().map(products -> toDTO(products)).collect(Collectors.toList());
    }

    public static List<Product> toEntity(List<productDTO> productDTOList) {
        return productDTOList.stream().map(productDto -> toEntity(productDto)).collect(Collectors.toList());
    }

}

