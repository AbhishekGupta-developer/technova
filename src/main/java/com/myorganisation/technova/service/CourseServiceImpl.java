package com.myorganisation.technova.service;

import com.myorganisation.technova.dto.request.CourseRequestDto;
import com.myorganisation.technova.dto.response.CourseResponseDto;
import com.myorganisation.technova.dto.response.GenericResponseDto;
import com.myorganisation.technova.model.Course;
import com.myorganisation.technova.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public CourseResponseDto addCourse(CourseRequestDto courseRequestDto) {
        Course course = new Course();
        course.setName(courseRequestDto.getName());
        course.setFee(courseRequestDto.getFee());
        course.setDuration(courseRequestDto.getDuration());

        courseRepository.save(course);

        course.setFaculty("Abhishek");

//        if(true) {
//            throw new RuntimeException();
//        }
        courseRepository.save(course);

        CourseResponseDto courseResponseDto = new CourseResponseDto();
        courseResponseDto.setId(course.getId());
        courseResponseDto.setName(course.getName());
        courseResponseDto.setFee(course.getFee());
        courseResponseDto.setDuration(course.getDuration());

        return courseResponseDto;
    }

    @Override
    public CourseResponseDto getCourse(Long id) {
        Course course = courseRepository.findById(id).orElse(null);

        CourseResponseDto courseResponseDto = new CourseResponseDto();
        courseResponseDto.setId(course.getId());
        courseResponseDto.setName(course.getName());
        courseResponseDto.setFee(course.getFee());
        courseResponseDto.setDuration(course.getDuration());

        return courseResponseDto;
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {
        List<Course> courseList = courseRepository.findAll();
        List<CourseResponseDto> courseResponseDtoList = new LinkedList<>();

        for(Course course : courseList) {
            CourseResponseDto courseResponseDto = new CourseResponseDto();
            courseResponseDto.setId(course.getId());
            courseResponseDto.setName(course.getName());
            courseResponseDto.setFee(course.getFee());
            courseResponseDto.setDuration(course.getDuration());

            courseResponseDtoList.add(courseResponseDto);
        }

        return courseResponseDtoList;
    }

    @Override
    public CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto) {
        Course course = courseRepository.findById(id).orElse(null);
        course.setName(courseRequestDto.getName());
        course.setFee(courseRequestDto.getFee());
        course.setDuration(courseRequestDto.getDuration());

        courseRepository.save(course);

        CourseResponseDto courseResponseDto = new CourseResponseDto();
        courseResponseDto.setId(course.getId());
        courseResponseDto.setName(course.getName());
        courseResponseDto.setFee(course.getFee());
        courseResponseDto.setDuration(course.getDuration());

        return courseResponseDto;
    }

    @Override
    public GenericResponseDto removeCourse(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        GenericResponseDto genericResponseDto = new GenericResponseDto();

        if(course != null) {
            String name = course.getName();
            courseRepository.deleteById(id);

            String message = "Course: " + name + "(" + id + ") has been removed";
            genericResponseDto.setSuccess(true);
            genericResponseDto.setMessage(message);
        } else {
            genericResponseDto.setSuccess(false);
            genericResponseDto.setMessage("Course doesn't exist");
        }

        return genericResponseDto;
    }

    @Override
    public Page<CourseResponseDto> getCoursePage(Integer pageIndex, Integer pageSize, String sortBy, String sortIn) {
        Sort sort = (sortIn.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending());

        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        Page<Course> coursePage = courseRepository.findAll(pageable);
        Page<CourseResponseDto> courseResponseDtoPage = coursePage.map(
                course -> mapCourseToCourseResponseDto(course)
        );

        return courseResponseDtoPage;
    }

    // Helper methods

    // Map Course into CourseResponseDto
    private CourseResponseDto mapCourseToCourseResponseDto(Course course) {
        if(course == null) {
            return null;
        }

        CourseResponseDto courseResponseDto = new CourseResponseDto();
        courseResponseDto.setId(course.getId());
        courseResponseDto.setName(course.getName());
        courseResponseDto.setFee(course.getFee());
        courseResponseDto.setDuration(course.getDuration());

        return courseResponseDto;
    }
}
