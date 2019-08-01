package rocks.lecomte.solver.service;

import rocks.lecomte.solver.SolverRequest;

public final class ServiceFactory {
    private ServiceFactory(){}

    private static PersistenceService<SolverRequest> persistenceService;
    private static ExecutorService<SolverRequest, String> solverService;

    static {
        synchronized (ServiceFactory.class) {
            persistenceService = new SolverPersistenceService();
            solverService = new SolverService();
        }
    }

    public static PersistenceService<SolverRequest> getPersistenceService() {
        return persistenceService;
    }


    public static ExecutorService<SolverRequest, String> getSolverService() {
        return solverService;
    }
}
