package com.myorganisation.technova.dto.response;

import com.myorganisation.technova.enums.Gender;
import com.myorganisation.technova.enums.UserRole;
import lombok.Data;

@Data
public class UserResponseDto {
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private Gender gender;
    private UserRole role;
}
