package software.engineer.szponka.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubClient {

  public ResponseEntity<String> repositoryResponse(String owner, String repositoryName) {
    RestTemplate restTemplate = new RestTemplate();
    String resourceUrl = "https://api.github.com/repos/" + owner + "/" + repositoryName;
    ResponseEntity<String> response = null;
    try {
      response = restTemplate.getForEntity(resourceUrl, String.class);
    } catch (HttpClientErrorException exception) {
      exception.printStackTrace();
    }
    return response;

  }
}
