package com.myorganisation.technova.dto.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentRequestDto {
    private String name;
    private List<Long> courses = new ArrayList<>();
    private String phone;
}
