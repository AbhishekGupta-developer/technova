package com.myorganisation.technova.controller;

import com.myorganisation.technova.dto.request.StudentRequestDto;
import com.myorganisation.technova.dto.response.GenericResponseDto;
import com.myorganisation.technova.dto.response.StudentResponseDto;
import com.myorganisation.technova.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponseDto> registerStudent(@RequestBody StudentRequestDto studentRequestDto) {
        return new ResponseEntity<>(studentService.registerStudent(studentRequestDto), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody StudentRequestDto studentRequestDto) {
        return new ResponseEntity<>(studentService.updateStudent(id, studentRequestDto), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> removeStudent(@RequestParam Long id) {
        return new ResponseEntity<>(studentService.removeStudent(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/find/phone/{phone}")
    public ResponseEntity<StudentResponseDto> getStudentByPhone(@PathVariable String phone) {
        return new ResponseEntity<>(studentService.getStudentByPhone(phone), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/find")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByNameAndCourse(@RequestParam String name, @RequestParam String course) {
        return new ResponseEntity<>(studentService.getStudentsByNameAndCourse(name, course), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/find/course/{course}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByCourse(@PathVariable String course) {
        return new ResponseEntity<>(studentService.getStudentsByCourse(course), HttpStatusCode.valueOf(200));
    }
}
