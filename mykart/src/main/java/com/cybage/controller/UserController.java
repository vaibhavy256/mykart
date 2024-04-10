package com.cybage.controller;

import com.cybage.config.JwtUtils;
import com.cybage.dto.userDTO;
import com.cybage.model.JwtRequest;
import com.cybage.model.JwtResponse;
import com.cybage.model.User;
import com.cybage.model.UserType;
import com.cybage.repository.IUserTypeRepository;
import com.cybage.service.CustomUserDetailsService;
import com.cybage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/addUser")
    public ResponseEntity<userDTO> signUp(@RequestBody userDTO userDto) throws Exception {
        User newUser = userService.addUser(userDTO.toEntity(userDto));
        userDTO dto = userDTO.toDTO(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody JwtRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        System.out.println(request.getUsername() + request.getPassword());
  //      UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername(), request.getPassword());
        String token = jwtUtils.generateToken(request.getUsername());
        JwtResponse response=new JwtResponse();
        response.setToken(token);
        return new ResponseEntity<JwtResponse>(response,HttpStatus.OK);

    }



    //@PostConstruct
    public void addAdminAtStartup() throws Exception {
        User user = new User();
        user.setUserId(1);
        user.setUsername("Rushi Kadam");
        user.setContactNo("9665208103");
        user.setEmail("rushibhau@gmail.com");
        user.setPassword(passwordEncoder.encode("123"));
        UserType adminRole = new UserType();
        adminRole.setUser_Role("ADMIN");
        if (user.getUserType() == null) {
            user.setUserType(new ArrayList<>());
        }
// Add the "ADMIN" role to the user's roles list
        user.getUserType().add(adminRole);
        userService.addAdmin(user);
    }
}
