package rocks.lecomte.solver;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StartSolverLambdaTest {
    private SolverRequest sample;
    private Context context;
    private SolverService serviceFactory;


    @Before
    public void before() throws Exception {
        context = mock(Context.class);
        when(context.getLogger()).thenReturn(mock(LambdaLogger.class));
        sample = new SolverRequest();

        SolverPersistenceService mF = mock(SolverPersistenceService.class);
        when(mF.create((SolverRequest)any())).thenReturn("111111");
        serviceFactory = new SolverService(new SolverPersistenceService());
    }

    @Test
    public void canStartSolving() {
        StartSolverLambda solver = new StartSolverLambda(serviceFactory);

        SolverResponse response = solver.handleRequest(sample, context);

        assertNotNull(response.getIdentifier());

    }

}
