package rocks.lecomte.solver;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SolverPersistenceService {
    public SolverRequest find(String identifier) throws IOException {
        DynamoDBMapper dbMapper = new DynamoDBMapper(ddb);
        ObjectMapper mapper = new ObjectMapper();
        SolverRequest response =  mapper.readValue(dbMapper.load(AutoGeneratedKey.class, identifier).getPayload(), SolverRequest.class);
        response.setIdentifier(identifier);
        return response;
    }
    final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();


    public String create(SolverRequest request) throws JsonProcessingException {
        AutoGeneratedKey obj = new AutoGeneratedKey();
        DynamoDBMapper dbMapper = new DynamoDBMapper(ddb);
        ObjectMapper mapper = new ObjectMapper();
        obj.setPayload(mapper.writeValueAsString(request));
        dbMapper.save(obj);

        return obj.getId();
    }
}