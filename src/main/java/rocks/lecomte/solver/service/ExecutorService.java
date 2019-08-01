package rocks.lecomte.solver.service;

public interface ExecutorService<I, R> {
    R process(I request);
}
