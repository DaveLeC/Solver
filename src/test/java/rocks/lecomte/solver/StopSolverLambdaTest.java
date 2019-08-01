package rocks.lecomte.solver;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.Before;
import org.junit.Test;
import rocks.lecomte.solver.lambda.StartSolverLambda;
import rocks.lecomte.solver.lambda.StopSolverLambda;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StopSolverLambdaTest {
    private SolverRequest sample;

    private Context context;

    @Before
    public void before() {
        context = mock(Context.class);
        when(context.getLogger()).thenReturn(mock(LambdaLogger.class));
        sample = new SolverRequest();

    }

    @Test
    public void canStopSolving() {
        StartSolverLambda start = new StartSolverLambda();

        SolverResponse response = start.handleRequest(sample, context);
        String identifier = response.getIdentifier();

        StopSolverLambda stop = new StopSolverLambda();
        assertSame(identifier, stop.handleRequest(identifier, context).getIdentifier());
    }

}
