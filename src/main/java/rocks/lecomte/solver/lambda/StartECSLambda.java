package rocks.lecomte.solver.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.AllArgsConstructor;
import rocks.lecomte.solver.SolverRequest;
import rocks.lecomte.solver.SolverResponse;
import rocks.lecomte.solver.service.ServiceFactory;

@AllArgsConstructor
public class StartECSLambda implements RequestHandler<SolverRequest, SolverResponse> {

    public SolverResponse handleRequest(SolverRequest request, Context context){
        try {
            context.getLogger().log("Entering");

            return new SolverResponse(ServiceFactory.getSolverService().process(request));


        } finally {
            context.getLogger().log("Exiting");
        }
    }

}
