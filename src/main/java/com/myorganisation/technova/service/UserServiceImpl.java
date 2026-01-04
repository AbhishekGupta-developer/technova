package com.myorganisation.technova.service;

import com.myorganisation.technova.dto.request.UserRequestDto;
import com.myorganisation.technova.dto.response.UserResponseDto;
import com.myorganisation.technova.exception.UserNotFoundException;
import com.myorganisation.technova.model.User;
import com.myorganisation.technova.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(userRequestDto.getPassword());

        userRepository.save(user);

        return mapUserToUserResponseDto(user);
    }

    @Override
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User id: " + id + " doesn't exist"));

        return mapUserToUserResponseDto(user);
    }

    // helper methods
    // Map User to UserResponseDto

    private UserResponseDto mapUserToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setUsername(user.getUsername());

        return userResponseDto;
    }
}
