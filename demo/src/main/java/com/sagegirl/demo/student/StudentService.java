package com.sagegirl.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
//        return List.of(new Student(1L, "aaa", "email", LocalDate.of(2000, 11, 11), 22));
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email is taken");
        }

        studentRepository.save(student);
//        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("student with id: "+studentId+" does not exist!");
        }

        studentRepository.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->
            new IllegalStateException("student with id: "+studentId+" does not exist!")
        );

        if (name != null && !name.isEmpty() && !name.isBlank() && !Objects.equals(name, student.getName())) {
            student.setName(name);
        }

        if (email != null && !email.isEmpty() && !email.isBlank() && !Objects.equals(email, student.getEmail())) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email is taken");
            }
            student.setName(email);
        }
    }
}
