package com.myorganisation.technova.service;

import com.myorganisation.technova.dto.request.UserRequestDto;
import com.myorganisation.technova.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto userRequestDto);
    UserResponseDto getUser(Long id);
}
