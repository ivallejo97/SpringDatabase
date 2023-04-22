package com.example.SpringDatabase;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserDTO {

    private Integer id;
    private String email;
    private String password;
    private String fullName;

    public UserDTO(Integer id, String email, String password, String fullName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public UserDTO(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.fullName = user.getFullName();
    }
}
