package dao;

import model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    @Override
    <S extends Employee> S save(S entity);

    @Override @Query(value="SELECT * FROM rb_employees WHERE ID = ?1", nativeQuery = true)
    Optional<Employee> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    Iterable<Employee> findAllById(Iterable<Integer> integers);

    @Override
    void deleteById(Integer integer);
}
