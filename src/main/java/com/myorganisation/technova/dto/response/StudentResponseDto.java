package com.myorganisation.technova.dto.response;

import com.myorganisation.technova.model.Account;
import lombok.Data;

@Data
public class StudentResponseDto {
    private Long id;
    private String name;
    private String course;
    private String phone;
    private Account account;
}
