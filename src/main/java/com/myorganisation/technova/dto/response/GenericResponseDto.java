package com.myorganisation.technova.dto.response;

import lombok.Data;

@Data
public class GenericResponseDto {
    Boolean success;
    String message;
    Object details;
}
