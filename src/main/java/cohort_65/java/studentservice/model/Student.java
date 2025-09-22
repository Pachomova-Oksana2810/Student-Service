package cohort_65.java.studentservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private  Integer id;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private Map<String, Integer> scores = new HashMap<>();
}
