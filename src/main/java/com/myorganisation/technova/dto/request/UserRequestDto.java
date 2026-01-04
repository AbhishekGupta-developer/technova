package com.myorganisation.technova.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
