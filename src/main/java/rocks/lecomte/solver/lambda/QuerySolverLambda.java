package rocks.lecomte.solver.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.NoArgsConstructor;
import rocks.lecomte.solver.SolverRequest;
import rocks.lecomte.solver.service.ServiceFactory;

@NoArgsConstructor
public class QuerySolverLambda implements RequestHandler<String, SolverRequest> {
    public SolverRequest handleRequest(String identifier, Context context) {
        try {
            context.getLogger().log("Entering " + identifier);
            return ServiceFactory.getPersistenceService().findById(identifier);
        } finally {
            context.getLogger().log("Exiting");
        }
    }
}
