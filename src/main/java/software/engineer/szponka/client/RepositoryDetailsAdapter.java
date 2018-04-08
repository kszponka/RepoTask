package software.engineer.szponka.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software.engineer.szponka.model.RepositoryDetails;

import java.io.IOException;

@Service
public class RepositoryDetailsAdapter {

  private final static Logger logger = LoggerFactory.getLogger(RepositoryDetailsAdapter.class);

  @Autowired
  private ObjectMapper mapper;

  public RepositoryDetails convertResponseEntityToJson(ResponseEntity<String> responseEntity) {

    RepositoryDetails repositoryDetails = new RepositoryDetails();
    JsonNode root = null;
    try {
      root = mapper.readTree(responseEntity.getBody());
    } catch (IOException e) {
      logger.warn("Problem with convertResponseEntityToJson - readTree exception", e);
    }

    if (root != null) {
      repositoryDetails.setFullName(root.path("full_name").asText());
      repositoryDetails.setDescription(root.path("description").asText());
      repositoryDetails.setCloneUrl(root.path("clone_url").asText());
      repositoryDetails.setStars(root.path("stargazers_count").asInt());
      repositoryDetails.setCreatedAt(root.path("created_at").asText());
      return repositoryDetails;
    } else {
      logger.warn("repositoryDetails is empty - JsonNode is null");
      return repositoryDetails;
    }
  }
}
