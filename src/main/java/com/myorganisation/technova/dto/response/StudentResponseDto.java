package com.myorganisation.technova.dto.response;

import com.myorganisation.technova.model.Account;
import lombok.Data;

import java.util.List;

@Data
public class StudentResponseDto {
    private Long id;
    private String name;
    private List<CourseResponseDto> courses;
    private String phone;
    private Account account;
}
