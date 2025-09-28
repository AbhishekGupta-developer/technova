package com.myorganisation.technova.dto.response;

import lombok.Data;

@Data
public class CourseResponseDto {
    private Long id;
    private String name;
    private Double fee;
    private Double duration;
}
