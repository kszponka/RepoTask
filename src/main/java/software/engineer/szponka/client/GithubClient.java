package software.engineer.szponka.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubClient {

  private final static Logger logger = LoggerFactory.getLogger(GithubClient.class);

  public ResponseEntity<String> repositoryResponse(String owner, String repositoryName) {
    RestTemplate restTemplate = new RestTemplate();
    String resourceUrl = "https://api.github.com/repos/" + owner + "/" + repositoryName;
    ResponseEntity<String> response = null;
    try {
      response = restTemplate.getForEntity(resourceUrl, String.class);
    } catch (HttpClientErrorException e) {
      logger.warn("Problem with repositoryResponse - wrong owner or repository name", e);
    }
    return response;

  }
}
