package cohort_65.java.studentservice.service;

import cohort_65.java.studentservice.dao.StudentRepository;
import cohort_65.java.studentservice.dto.NewStudentDto;
import cohort_65.java.studentservice.dto.ScoreDto;
import cohort_65.java.studentservice.dto.StudentDto;
import cohort_65.java.studentservice.dto.UpdateStudentDto;
import cohort_65.java.studentservice.dto.exception.InvalidStudentRequestException;
import cohort_65.java.studentservice.dto.exception.StudentNotFoundException;
import cohort_65.java.studentservice.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public boolean addStudent(NewStudentDto newStudent) {
        if (newStudent == null) {
            throw new InvalidStudentRequestException("Request body is required");
        }
        if (newStudent.getId() == null || newStudent.getId() < 0) {
            throw new InvalidStudentRequestException("Field 'id' must be a non-negative integer");
        }
        if (newStudent.getFirstName() == null || newStudent.getFirstName().trim().isEmpty()) {
            throw new InvalidStudentRequestException("Field 'firstName' must be a non-empty string");
        }
        if (newStudent.getLastName() == null || newStudent.getLastName().trim().isEmpty()) {
            throw new InvalidStudentRequestException("Field 'lastName' must be a non-empty string");
        }
        if (studentRepository.findById(newStudent.getId()).isPresent()) {
            return false;
        }
        Student student = new Student(newStudent.getId(), newStudent.getFirstName(), newStudent.getLastName());
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName());
    }

    @Override
    public StudentDto removeStudent(int id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        studentRepository.deleteById(id);
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName());
    }

    @Override
    public StudentDto updateStudent(UpdateStudentDto updateStudent, int id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        if (updateStudent.getFirstName() != null) {
            student.setFirstName(updateStudent.getFirstName());
        }
        if (updateStudent.getLastName() != null) {
            student.setLastName(updateStudent.getLastName());
        }
        studentRepository.save(student);
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName());
    }

    @Override
    public Boolean addScore(int studentId, ScoreDto scoreDto) {
        Student student = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        boolean result = student.addScore(scoreDto.getExam(), scoreDto.getScore());
        studentRepository.save(student);
        return result;
    }

    @Override
    public List<StudentDto> findStudentsByName(String name) {
            return studentRepository.findAll()
                    .stream()
                    .filter(student -> student.getFirstName().contains(name))
                    .map(student -> new StudentDto(student.getId(),
                            student.getFirstName(), student.getLastName()))
                    .toList();
        }

    @Override
    public Integer getStudentsNamesQuantity(Set<String> names) {
        return Math.toIntExact(studentRepository.countByFirstNameIn(names));
    }

    @Override
    public List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore) {
        return studentRepository.findByExamMinScore(exam, minScore)
                .map(student -> new StudentDto(student.getId(),
                        student.getFirstName(), student.getLastName()))
                .toList();
    }

}
