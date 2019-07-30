package rocks.lecomte.solver;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class QuerySolverLambda implements RequestHandler<String, SolverRequest> {
    private SolverPersistenceService solverPersistenceService;

    public SolverRequest handleRequest(String identifier, Context context) {
        try {
            context.getLogger().log("Entering");
            SolverRequest response = solverPersistenceService.find(identifier);
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            context.getLogger().log("Exiting");
        }
    }

    private String sendMessage(SolverRequest request) {
        return toString();
    }
}
