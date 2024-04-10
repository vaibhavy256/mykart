package com.cybage.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {
    String Username;
    String Password;

    public JwtRequest(String username, String password) {
        Username = username;
        Password = password;
    }

    public JwtRequest() {
    }
}
