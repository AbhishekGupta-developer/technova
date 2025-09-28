package com.myorganisation.technova.dto.request;

import lombok.Data;

@Data
public class CourseRequestDto {
    private String name;
    private Double fee;
    private Double duration;
}
