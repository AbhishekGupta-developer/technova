package com.myorganisation.technova.dto.request;

import com.myorganisation.technova.enums.Gender;
import com.myorganisation.technova.enums.UserRole;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String firstName;
    private String lastName;
    private Gender gender;
    private UserRole role;
    private String username;
    private String password;
}
