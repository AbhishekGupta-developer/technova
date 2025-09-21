package com.myorganisation.technova.service;

import com.myorganisation.technova.dto.request.StudentRequestDto;
import com.myorganisation.technova.dto.response.GenericResponseDto;
import com.myorganisation.technova.dto.response.StudentResponseDto;

import java.util.List;

public interface StudentService {
    StudentResponseDto registerStudent(StudentRequestDto studentRequestDto);
    StudentResponseDto getStudent(Long id);
    List<StudentResponseDto> getAllStudents();
    StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequestDto);
    GenericResponseDto removeStudent(Long id);

    // Custom finder method
    StudentResponseDto getStudentByPhone(String phone);
    List<StudentResponseDto> getStudentsByNameAndCourse(String name, String course);

    List<StudentResponseDto> getStudentsByCourse(String course);

    List<StudentResponseDto> searchStudentsByName(String name);
}
