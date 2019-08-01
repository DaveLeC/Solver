package rocks.lecomte.solver.service;

public interface PersistenceService<R> {
     R findById(String id);
     R findByDate(String date);
     void save(R obj);

}
