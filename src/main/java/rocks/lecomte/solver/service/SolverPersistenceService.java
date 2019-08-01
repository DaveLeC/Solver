package rocks.lecomte.solver.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rocks.lecomte.solver.SolverRequest;
import rocks.lecomte.solver.model.AutoGeneratedKey;
import java.io.IOException;

public class SolverPersistenceService implements PersistenceService<SolverRequest> {
    private final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();

    @Override
    public SolverRequest findById(String identifier) {
        DynamoDBMapper dbMapper = new DynamoDBMapper(ddb);
        ObjectMapper mapper = new ObjectMapper();
        AutoGeneratedKey agk = dbMapper.load(AutoGeneratedKey.class, identifier);

        SolverRequest response;
        try {
            response = mapper.readValue(agk.getRequest(), SolverRequest.class);
        } catch (IOException e) {
            throw new SolverException("Unable to obtain the request for " + identifier, e);
        }
        response.setIdentifier(identifier);
        return response;
    }

    @Override
    public SolverRequest findByDate(String date) {
        return null;
    }

    @Override
    public void save(SolverRequest request) {
        AutoGeneratedKey key = new AutoGeneratedKey();
        DynamoDBMapper dbMapper = new DynamoDBMapper(ddb);
        ObjectMapper mapper = new ObjectMapper();
        try {
            key.setRequest(mapper.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            throw new SolverException("Unable to save the request", e);
        }
        dbMapper.save(key);

        request.setIdentifier(key.getId());
    }
}
