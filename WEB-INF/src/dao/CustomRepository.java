package dao;
/**
 * CustomRepository
 */

import java.util.List;

public interface CustomRepository<T> {
    List<T> findAll();

    void deleteAll();

    T save(T t);
}
