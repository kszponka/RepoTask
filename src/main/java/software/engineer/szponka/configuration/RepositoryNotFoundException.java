package software.engineer.szponka.configuration;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Such Repository")
public class RepositoryNotFoundException extends RuntimeException {

}
