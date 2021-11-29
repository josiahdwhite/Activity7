package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private StudentService studentService;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
@GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    
@PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);

    }

    void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }
    public void deleteStudent(Long studentId) {
        studentRepository.findById(studentId);
                boolean exists = studentRepository.existsById(studentId);
                        if (!exists) {
                            throw new IllegalStateException(
                                    "student with id " +studentId + " does not exist"
                            );
                        }

    }
}
