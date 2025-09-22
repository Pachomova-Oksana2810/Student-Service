package cohort_65.java.studentservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private  Integer id;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
}
