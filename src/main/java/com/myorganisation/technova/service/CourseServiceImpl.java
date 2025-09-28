package com.myorganisation.technova.service;

import com.myorganisation.technova.dto.request.CourseRequestDto;
import com.myorganisation.technova.dto.response.CourseResponseDto;
import com.myorganisation.technova.dto.response.GenericResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Override
    public CourseResponseDto addCourse(CourseRequestDto courseRequestDto) {
        return null;
    }

    @Override
    public CourseResponseDto getCourse(Long id) {
        return null;
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {
        return List.of();
    }

    @Override
    public CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto) {
        return null;
    }

    @Override
    public GenericResponseDto removeCourse(Long id) {
        return null;
    }
}
