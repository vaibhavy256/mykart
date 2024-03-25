package com.cybage.dto;

import com.cybage.model.Category;
import com.cybage.model.CategoryType;
import com.cybage.model.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class categoryDTO {
    private CategoryType categoryType;
    private String description;


    public static Category toEntity(categoryDTO dto) {
        Category entity = new Category();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static categoryDTO toDTO(Category entity) {
        categoryDTO dto = new categoryDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static List<categoryDTO> toDTO(List<Category> categoryList) {

        return categoryList.stream().map(user -> toDTO(user)).collect(Collectors.toList());
    }

    public static List<Category> toEntity(List<categoryDTO> categoryDTOList) {
        return categoryDTOList.stream().map(categoryDto -> toEntity(categoryDto)).collect(Collectors.toList());
    }

}
