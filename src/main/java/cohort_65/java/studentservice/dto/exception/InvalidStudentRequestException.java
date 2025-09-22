package cohort_65.java.studentservice.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidStudentRequestException extends RuntimeException {
    public InvalidStudentRequestException(String message) {
        super(message);
    }

}
