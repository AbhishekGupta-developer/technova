package com.myorganisation.technova.service;

import com.myorganisation.technova.dto.request.StudentRequestDto;
import com.myorganisation.technova.dto.response.CourseResponseDto;
import com.myorganisation.technova.dto.response.GenericResponseDto;
import com.myorganisation.technova.dto.response.StudentResponseDto;
import com.myorganisation.technova.exception.StudentNotFoundException;
import com.myorganisation.technova.model.Account;
import com.myorganisation.technova.model.Course;
import com.myorganisation.technova.model.Student;
import com.myorganisation.technova.repository.CourseRepository;
import com.myorganisation.technova.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

//    @Autowired
//    private AccountRepository accountRepository;

    @Override
    public StudentResponseDto registerStudent(StudentRequestDto studentRequestDto) {
        Student student = mapStudentRequestDtoToStudent(studentRequestDto, new Student());

        //Manual way
//        Account account = new Account();
//        account.setTotal(0D);
//        account.setDue(0D);
//
//        accountRepository.save(account);
//
//        student.setAccount(account);
//
//        studentRepository.save(student);
//
//        account.setStudent(student);
//
//        accountRepository.save(account);

        //Using cascade
        Account account = new Account();
        account.setTotal(0D);
        account.setDue(0D);

        student.setAccount(account);
        account.setStudent(student);

        studentRepository.save(student);

        return mapStudentToStudentResponseDto(student);
    }

    @Override
    public StudentResponseDto getStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(
                        () -> new StudentNotFoundException("Student id: " + id + " doesn't exist.")
                );

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

    @Override
    public StudentResponseDto getStudentByPhone(String phone) {
        return mapStudentToStudentResponseDto(studentRepository.findByPhone(phone).orElse(null));
    }

    @Override
    public List<StudentResponseDto> getStudentsByNameAndCourse(String name, String course) {
        List<Student> studentList = new LinkedList<>(studentRepository.findByNameAndCourse(name, course));
        List<StudentResponseDto> studentResponseDtoList = new LinkedList<>();

        for(Student student : studentList) {
            studentResponseDtoList.add(mapStudentToStudentResponseDto(student));
        }

        return studentResponseDtoList;
    }

    @Override
    public List<StudentResponseDto> getStudentsByCourse(String course) {
        List<Student> studentList = new LinkedList<>(studentRepository.getStudentsByCourse(course));
        List<StudentResponseDto> studentResponseDtoList = new LinkedList<>();

        for(Student student : studentList) {
            studentResponseDtoList.add(mapStudentToStudentResponseDto(student));
        }

        return studentResponseDtoList;
    }

    @Override
    public List<StudentResponseDto> searchStudentsByName(String name) {
        List<Student> studentList = new LinkedList<>(studentRepository.searchStudentsByName(name));
        List<StudentResponseDto> studentResponseDtoList = new LinkedList<>();

        for(Student student : studentList) {
            studentResponseDtoList.add(mapStudentToStudentResponseDto(student));
        }

        return studentResponseDtoList;
    }

    @Override
    public List<StudentResponseDto> searchStudentsByCourseAndPhone(String course, String phone) {
        List<Student> studentList = new LinkedList<>(studentRepository.searchStudentsByCourseAndPhone(course, phone));
        List<StudentResponseDto> studentResponseDtoList = new LinkedList<>();

        for(Student student : studentList) {
            studentResponseDtoList.add(mapStudentToStudentResponseDto(student));
        }

        return studentResponseDtoList;
    }

    // Helper method

    // Map Student to StudentResponseDto
    private StudentResponseDto mapStudentToStudentResponseDto(Student student) {
        List<Course> courseList = student.getCourses();
        List<CourseResponseDto> courseResponseDtoList = new ArrayList<>();

        for(Course course : courseList) {
            CourseResponseDto courseResponseDto = new CourseResponseDto();
            courseResponseDto.setId(course.getId());
            courseResponseDto.setName(course.getName());
            courseResponseDto.setFee(course.getFee());
            courseResponseDto.setDuration(course.getDuration());

            courseResponseDtoList.add(courseResponseDto);
        }

        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setCourses(courseResponseDtoList);
        studentResponseDto.setPhone(student.getPhone());
        studentResponseDto.setAccount(student.getAccount());

        return studentResponseDto;
    }

    // Map StudentRequestDto to Student
    private Student mapStudentRequestDtoToStudent(StudentRequestDto studentRequestDto, Student student) {
        student.setName(studentRequestDto.getName());
        student.setPhone(studentRequestDto.getPhone());

        List<Long> courses = studentRequestDto.getCourses();
        List<Course> courseList = new ArrayList<>();

        for(Long courseId : courses) {
            courseList.add(courseRepository.findById(courseId).orElse(null));
        }

        student.setCourses(courseList);

        return student;
    }
}
