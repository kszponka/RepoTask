package software.engineer.szponka.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import software.engineer.szponka.client.GithubClient;
import software.engineer.szponka.client.RepositoryDetailsAdapter;
import software.engineer.szponka.configuration.RepositoryNotFoundException;
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
      @PathVariable String repositoryName) {

    ResponseEntity<String> response = githubClient.repositoryResponse(owner, repositoryName);
    if (response == null) {
      throw new RepositoryNotFoundException();
    }
    return repositoryDetailsAdapter.convertResponseEntityToJson(response);
  }
}
