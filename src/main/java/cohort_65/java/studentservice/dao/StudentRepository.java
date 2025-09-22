package cohort_65.java.studentservice.dao;

import cohort_65.java.studentservice.model.Student;

import java.util.Collection;
import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(Integer id);
    Collection<Student> findAll();
    void deleteById(Integer id);
}
