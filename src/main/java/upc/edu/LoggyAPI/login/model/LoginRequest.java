package upc.edu.LoggyAPI.login.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
