package software.engineer.szponka.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubClient {

  public ResponseEntity<String> repositoryResponse(String owner, String repositoryName) {
    RestTemplate restTemplate = new RestTemplate();
    String resourceUrl = "https://api.github.com/repos/" + owner + "/" + repositoryName;
    return restTemplate.getForEntity(resourceUrl, String.class);

  }
}
