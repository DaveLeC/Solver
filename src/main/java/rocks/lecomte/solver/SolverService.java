package rocks.lecomte.solver;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SolverService {
    private SolverPersistenceService persistenceService;


    public String process(SolverRequest request) throws JsonProcessingException {
        String msgId = persistenceService.create(request);

        return msgId;
    }
}
