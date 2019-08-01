package rocks.lecomte.solver;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import org.junit.Before;
import org.junit.Test;
import rocks.lecomte.solver.lambda.QuerySolverLambda;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuerySolverLambdaTest {
    private static final String TEST_ID = "0818d114-13fa-4ec1-8191-4d7d3b4e94eb";
    private Context context;
    @Before
    public void before() {
        context = mock(Context.class);
        when(context.getLogger()).thenReturn(mock(LambdaLogger.class));
    }

    @Test
    public void canFind() {
        QuerySolverLambda sl=new QuerySolverLambda();

        SolverRequest response = sl.handleRequest(TEST_ID, context);

        assertSame(response.getIdentifier(), TEST_ID);
    }
}
