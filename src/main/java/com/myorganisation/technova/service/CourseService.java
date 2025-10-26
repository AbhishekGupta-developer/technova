package com.myorganisation.technova.service;

import com.myorganisation.technova.dto.request.CourseRequestDto;
import com.myorganisation.technova.dto.response.CourseResponseDto;
import com.myorganisation.technova.dto.response.GenericResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    CourseResponseDto addCourse(CourseRequestDto courseRequestDto);
    CourseResponseDto getCourse(Long id);
    List<CourseResponseDto> getAllCourses();
    CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto);
    GenericResponseDto removeCourse(Long id);

    Page<CourseResponseDto> getCoursePage(Integer pageIndex, Integer pageSize, String sortBy, String sortIn);
}
