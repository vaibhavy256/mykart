package com.cybage.dto;

import com.cybage.model.Address;
import com.cybage.model.Product;
import com.cybage.model.User;
import com.cybage.model.UserType;
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
public class userDTO {

    private int userId;
    private String username;

    private String password;
    private String email;
    private String contactNo;
    private List<UserType> userType;
    private List<Product> products;
    private List<Address> addresses;


    public static User toEntity(userDTO dto) {
        User entity = new User();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static userDTO toDTO(User entity) {
        userDTO dto = new userDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static List<userDTO> toDTO(List<User> userList) {

        return userList.stream().map(user -> toDTO(user)).collect(Collectors.toList());
    }

    public static List<User> toEntity(List<userDTO> userDTOList) {
        return userDTOList.stream().map(userDto -> toEntity(userDto)).collect(Collectors.toList());
    }


}
