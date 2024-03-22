package com.cybage.dto;

import com.cybage.model.Address;
import com.cybage.model.Product;
import com.cybage.model.User;
import com.cybage.model.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class userDto {

    private int userId;
    private String userName;
    private String email;
    private String contactNo;
    private UserType userType;
    private List<Product> products;
    private List<Address> addresses;


    public static User toEntity(userDto dto) {
        User entity = new User();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static userDto toDTO(User entity) {
        userDto dto = new userDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static List<userDto> toDTO(List<User> userList) {

        return userList.stream().map(user -> toDTO(user)).collect(Collectors.toList());
    }

    public static List<User> toEntity(List<userDto> userDTOList) {
        return userDTOList.stream().map(userDto -> toEntity(userDto)).collect(Collectors.toList());
    }


}