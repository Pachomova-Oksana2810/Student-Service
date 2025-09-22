package cohort_65.java.studentservice.service;

import cohort_65.java.studentservice.dto.NewStudentDto;
import cohort_65.java.studentservice.dto.ScoreDto;
import cohort_65.java.studentservice.dto.StudentDto;
import cohort_65.java.studentservice.dto.UpdateStudentDto;

import java.util.List;
import java.util.Set;


public interface StudentService {

    boolean addStudent(NewStudentDto newStudentDto);

    StudentDto findStudent(Integer id);

    StudentDto removeStudent(int id);

    StudentDto updateStudent(UpdateStudentDto updateStudent, int id);

    Boolean addScore(int studentId, ScoreDto scoreDto);
    List<StudentDto> findStudentsByName(String name);
    Integer getStudentsNamesQuantity(Set<String>  names);
    List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore);

}
