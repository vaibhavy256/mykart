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

    private Category category_id;

    private User seller_id;

    private String userEmail;

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


}

