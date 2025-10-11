package com.myorganisation.technova.controller;

import com.myorganisation.technova.dto.request.CourseRequestDto;
import com.myorganisation.technova.dto.response.CourseResponseDto;
import com.myorganisation.technova.dto.response.GenericResponseDto;
import com.myorganisation.technova.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponseDto> addCourse(@RequestBody CourseRequestDto courseRequestDto) {
        return new ResponseEntity<>(courseService.addCourse(courseRequestDto), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getCourse(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getCourse(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDto courseRequestDto) {
        return new ResponseEntity<>(courseService.updateCourse(id, courseRequestDto), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> removeCourse(@RequestParam Long id) {
        return new ResponseEntity<>(courseService.removeCourse(id), HttpStatusCode.valueOf(200));
    }
}
