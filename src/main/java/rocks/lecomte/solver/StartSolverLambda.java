package rocks.lecomte.solver;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StartSolverLambda implements RequestHandler<SolverRequest, SolverResponse> {
    private SolverService serviceFactory;

    public SolverResponse handleRequest(SolverRequest request, Context context){
        try {
            context.getLogger().log("Entering");

            return new SolverResponse(serviceFactory.process(request));

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            context.getLogger().log("Exiting");
        }
    }

}
