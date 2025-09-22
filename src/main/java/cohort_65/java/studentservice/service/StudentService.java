package cohort_65.java.studentservice.service;

import cohort_65.java.studentservice.dto.NewStudentDto;
import cohort_65.java.studentservice.dto.StudentDto;
import cohort_65.java.studentservice.dto.UpdateStudentDto;


public interface StudentService {

    boolean addStudent(NewStudentDto newStudentDto);

    StudentDto findStudent(Integer id);

    StudentDto removeStudent(int id);

    StudentDto updateStudent(UpdateStudentDto updateStudent, int id);
}
