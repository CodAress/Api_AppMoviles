package upc.edu.LoggyAPI.user.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String genre;
    private LocalDate birthdate;
    private String address;
}
