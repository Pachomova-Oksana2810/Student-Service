package cohort_65.java.studentservice.dao;

import cohort_65.java.studentservice.model.Student;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    Map<Integer, Student> students = new ConcurrentHashMap<>();

    @Override
    public Student save(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return Optional.ofNullable(students.get(id));
    }

    @Override
    public Collection<Student> findAll() {
        return students.values();
    }

    @Override
    public void deleteById(Integer id) {
        students.remove(id);

    }
}
