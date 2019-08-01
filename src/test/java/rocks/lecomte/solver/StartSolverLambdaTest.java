package rocks.lecomte.solver;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.Before;
import org.junit.Test;
import rocks.lecomte.solver.lambda.StartSolverLambda;




import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StartSolverLambdaTest {
    private SolverRequest sample;
    private Context context;



    @Before
    public void before() {
        context = mock(Context.class);
        when(context.getLogger()).thenReturn(mock(LambdaLogger.class));
        sample = new SolverRequest();

    }

    @Test
    public void canStartSolving() {
        StartSolverLambda solver = new StartSolverLambda();

        SolverResponse response = solver.handleRequest(sample, context);

        assertNotNull(response.getIdentifier());

    }

}
