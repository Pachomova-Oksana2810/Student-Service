package cohort_65.java.studentservice.dao;

import cohort_65.java.studentservice.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public interface StudentRepository  extends MongoRepository<Student, Integer> {
    Stream<Student> findByFirstNameIgnoreCase(String name);

    long countByFirstNameIn(Set<String> names);

    @Query(value = "{ 'scores.?0': { $gte: ?1 } }")
    Stream<Student> findByExamMinScore(String exam, Integer minScore);

}
