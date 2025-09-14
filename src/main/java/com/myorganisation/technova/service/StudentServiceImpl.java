package com.myorganisation.technova.service;

import com.myorganisation.technova.dto.request.StudentRequestDto;
import com.myorganisation.technova.dto.response.GenericResponseDto;
import com.myorganisation.technova.dto.response.StudentResponseDto;
import com.myorganisation.technova.model.Student;
import com.myorganisation.technova.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentResponseDto registerStudent(StudentRequestDto studentRequestDto) {
        Student student = mapStudentRequestDtoToStudent(studentRequestDto, new Student());

        studentRepository.save(student);

        return mapStudentToStudentResponseDto(student);
    }

    @Override
    public StudentResponseDto getStudent(Long id) {
        Student student = studentRepository.findById(id).orElse(null);

        return mapStudentToStudentResponseDto(student);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> studentList = new LinkedList<>(studentRepository.findAll());
        List<StudentResponseDto> studentResponseDtoList = new LinkedList<>();

        for(Student student : studentList) {
            studentResponseDtoList.add(mapStudentToStudentResponseDto(student));
        }

        return studentResponseDtoList;
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequestDto) {
        Student student = studentRepository.findById(id).orElse(null);

        student = mapStudentRequestDtoToStudent(studentRequestDto, student);

        studentRepository.save(student);

        return mapStudentToStudentResponseDto(student);
    }

    @Override
    public GenericResponseDto removeStudent(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        GenericResponseDto genericResponseDto = new GenericResponseDto();

        if(student != null) {
            String name = student.getName();

            studentRepository.deleteById(id);

            genericResponseDto.setSuccess(true);
            genericResponseDto.setMessage("Student name: " + name + " (" + id + ") has bee removed successfully");
        } else {
            genericResponseDto.setSuccess(false);
            genericResponseDto.setMessage("Student id: " + id + ", not found");
        }

        return genericResponseDto;
    }

    // Helper method

    // Map Student to StudentResponseDto
    private StudentResponseDto mapStudentToStudentResponseDto(Student student) {
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setCourse(student.getCourse());
        studentResponseDto.setPhone(student.getPhone());

        return studentResponseDto;
    }

    // Map StudentRequestDto to Student
    private Student mapStudentRequestDtoToStudent(StudentRequestDto studentRequestDto, Student student) {
        student.setName(studentRequestDto.getName());
        student.setCourse(studentRequestDto.getCourse());
        student.setPhone(studentRequestDto.getPhone());

        return student;
    }
}
