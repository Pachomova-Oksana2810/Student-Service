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
        if (scoreDto == null || scoreDto.getExam() == null || scoreDto.getExam().trim().isEmpty() || scoreDto.getScore() == null) {
            throw new InvalidStudentRequestException("Fields 'exam' and 'score' are required");
        }
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        student.getScores().put(scoreDto.getExam(), scoreDto.getScore());
        studentRepository.save(student);
        return true;
    }

    @Override
    public List<StudentDto> findStudentsByName(String name) {
        String lower = name.toLowerCase();
        List<StudentDto> result = new ArrayList<>();
        for (Student s : studentRepository.findAll()) {
            String full = (s.getFirstName() + " " + s.getLastName()).toLowerCase();
            if (s.getFirstName().toLowerCase().contains(lower) || s.getLastName().toLowerCase()
                    .contains(lower) || full.contains(lower)) {
                result.add(new StudentDto(s.getId(), s.getFirstName(), s.getLastName()));
            }
        }
        return result;
    }

    @Override
    public Integer getStudentsNamesQuantity(Set<String> names) {
        if (names == null || names.isEmpty()) {
            return 0;
        }
        Set<String> lowered = new HashSet<>();
        for (String n : names) {
            if (n != null) {
                lowered.add(n.toLowerCase());
            }
        }
        int count = 0;
        for (Student s : studentRepository.findAll()) {
            if (lowered.contains(s.getFirstName().toLowerCase()) || lowered.contains(s.getLastName().toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore) {
        List<StudentDto> result = new ArrayList<>();
        if (exam == null || exam.trim().isEmpty() || minScore == null) {
            return result;
        }
        for (Student s : studentRepository.findAll()) {
            Integer score = s.getScores().get(exam);
            if (score != null && score >= minScore) {
                result.add(new StudentDto(s.getId(), s.getFirstName(), s.getLastName()));
            }
        }
        return result;
    }

}
