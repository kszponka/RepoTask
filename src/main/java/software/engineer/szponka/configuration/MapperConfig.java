package software.engineer.szponka.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MapperConfig {

  @Bean
  @Primary
  public ObjectMapper getMapper() {
    return new ObjectMapper();

  }
}
