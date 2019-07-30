package rocks.lecomte.solver;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StopSolverLambdaTest {
    private SolverRequest sample;
    private SolverService serviceFactory;
    private Context context;

    @Before
    public void before() {
        context = mock(Context.class);
        when(context.getLogger()).thenReturn(mock(LambdaLogger.class));
        sample = new SolverRequest();
        serviceFactory = new SolverService(mock(SolverPersistenceService.class));

    }

    @Test
    public void canStopSolving() {
        StartSolverLambda start = new StartSolverLambda(serviceFactory);

        SolverResponse response = start.handleRequest(sample, context);
        String identifier = response.getIdentifier();

        StopSolverLambda stop = new StopSolverLambda();
        assertSame(identifier, stop.handleRequest(identifier, context).getIdentifier());
    }

}
