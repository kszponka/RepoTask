package software.engineer.szponka.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import software.engineer.szponka.client.GithubClient;
import software.engineer.szponka.client.RepositoryDetailsAdapter;
import software.engineer.szponka.model.RepositoryDetails;

import java.io.IOException;

@RestController
public class Controller {

  @Autowired
  private GithubClient githubClient;
  @Autowired
  private RepositoryDetailsAdapter repositoryDetailsAdapter;

  @RequestMapping(value = "/repositories/{owner}/{repositoryName}", method = RequestMethod.GET)

  public RepositoryDetails getRepositoryDetails(
      @PathVariable String owner,
      @PathVariable String repositoryName) throws IOException {

    ResponseEntity<String> response = githubClient.repositoryResponse(owner, repositoryName);
    return repositoryDetailsAdapter.convertResponseEntityToJson(response);



//    RestTemplate restTemplate = new RestTemplate();
//    String resourceUrl = "https://api.github.com/repos/skrzychuz/PokerChips2";
//    ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
//
//    RepositoryDetails repositoryDetails = new RepositoryDetails();
//    ObjectMapper mapper = new ObjectMapper();
//
//
//    JsonNode root = mapper.readTree(response.getBody());
//    repositoryDetails.setFullName(root.path("full_name").asText());
//    repositoryDetails.setCreatedAt(root.path("created_at").asText());
//    repositoryDetails.setStars(root.path("stargazers_count").asInt());
//    repositoryDetails.setDescription(root.path("description").asText());
//    repositoryDetails.setCloneUrl(root.path("clone_url").asText());


  }
}
