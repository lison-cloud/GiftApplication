package by.bsuir.system.repository;

import java.util.List;
import java.util.Optional;

public interface BaseCrudRepository<T, U> {

    List<T> getAll();

    U add(T t);

    Optional<T> get(U u);

    boolean delete(U u);

    boolean update(T t);
}
