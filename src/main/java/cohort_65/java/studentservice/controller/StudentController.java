package cohort_65.java.studentservice.controller;

import cohort_65.java.studentservice.dto.NewStudentDto;
import cohort_65.java.studentservice.dto.ScoreDto;
import cohort_65.java.studentservice.dto.StudentDto;
import cohort_65.java.studentservice.dto.UpdateStudentDto;
import cohort_65.java.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public boolean addStudent(@RequestBody NewStudentDto newStudentDto) {
        return studentService.addStudent(newStudentDto);
    }
    @GetMapping("/student/{id}")
    public StudentDto findStudent(@PathVariable int id) {
        return studentService.findStudent(id);
    }
    @DeleteMapping("/student/{id}")
    public StudentDto removeStudent(@PathVariable int id) {
        return studentService.removeStudent(id);
    }

    @PutMapping("/student/{id}")
    public StudentDto updateStudent(@RequestBody UpdateStudentDto updateStudent,
                                    @PathVariable int id) {
        return studentService.updateStudent(updateStudent, id);
    }

    @PutMapping("/score/student/{id}")
    public Boolean addScore(@PathVariable int id, @RequestBody ScoreDto scoreDto) {
        return studentService.addScore(id, scoreDto);
    }

    @GetMapping("/students/name/{name}")
    public List<StudentDto> findStudentsByName(@PathVariable String name) {
        return studentService.findStudentsByName(name);
    }

    @PostMapping("/quantity/students")
    public Integer getStudentsNamesQuantity(@RequestBody Set<String> names) {
        return studentService.getStudentsNamesQuantity(names);
    }

    @GetMapping("/students/exam/{exam}/minscore/{minScore}")
    public List<StudentDto> getStudentsByExamMinScore(@PathVariable String exam, @PathVariable Integer minScore) {
        return studentService.getStudentsByExamMinScore(exam, minScore);
    }

}







