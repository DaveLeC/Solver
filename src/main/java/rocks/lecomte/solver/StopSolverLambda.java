package rocks.lecomte.solver;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class StopSolverLambda implements RequestHandler<String, SolverResponse> {

    public SolverResponse handleRequest(String identifier, Context context){
        try {
            context.getLogger().log("Entering");

            return new SolverResponse(identifier);
        } finally {
            context.getLogger().log("Exiting");
        }
    }
}
