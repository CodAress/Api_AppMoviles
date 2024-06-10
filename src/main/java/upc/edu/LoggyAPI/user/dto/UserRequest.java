package upc.edu.LoggyAPI.user.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String role;
}
