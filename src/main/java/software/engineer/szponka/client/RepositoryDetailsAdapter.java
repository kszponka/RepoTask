package software.engineer.szponka.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software.engineer.szponka.model.RepositoryDetails;

import java.io.IOException;

@Service
public class RepositoryDetailsAdapter {

  @Autowired
  ObjectMapper mapper;

  public RepositoryDetails convertResponseEntityToJson(ResponseEntity<String> responseEntity)
      throws IOException {

    RepositoryDetails repositoryDetails = new RepositoryDetails();

    JsonNode root = mapper.readTree(responseEntity.getBody());
    repositoryDetails.setFullName(root.path("full_name").asText());
    repositoryDetails.setDescription(root.path("description").asText());
    repositoryDetails.setCloneUrl(root.path("clone_url").asText());
    repositoryDetails.setStars(root.path("stargazers_count").asInt());
    repositoryDetails.setCreatedAt(root.path("created_at").asText());

    return repositoryDetails;
  }
}
