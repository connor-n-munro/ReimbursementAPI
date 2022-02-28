package dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j @NoArgsConstructor
public class EmployeePostgresDAO implements EmployeeRepository {

    private EntityManager entityManager;

    @Autowired
    public EmployeePostgresDAO(EntityManager e) {
        this.entityManager = e;
    }

    /**
     * Saves a new employee to the postgres database
     * @param employee the employee object to be saved into the DB
     */
    @Override
    public Employee save(@NotNull Employee employee) {
        // STEP ZERO : Loggin'
        Date date = new Date(System.currentTimeMillis());
        log.info("Saving employee \"{}\" to the postgres database at time {}", employee.getUsername(), date);
        // STEP ONE : Prepare connection to postgres database
        Session currentSession = entityManager.unwrap(Session.class);
        // STEP TWO : Save the employee
        Transaction tx = currentSession.beginTransaction();
        try {
            /*transactional work, thrown into a try-catch bc of high frequency of errors*/
            currentSession.saveOrUpdate(employee);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            log.error("ERROR saving employee to postgres database at time {}: {}", date, e);
        }
        return employee;
    }

    /**
     * Returns a list of all employee objects in the postgreSQL database
     * @return a List filled with all of the employees saved
     */
    public List<Employee> getAll() {
        // STEP ZERO : Loggin'
        Date date = new Date(System.currentTimeMillis());
        log.info("Listing all employees at time {}", date);

        // STEP ONE : Prepare connection to postgres database and the result list
        Session currentSession = entityManager.unwrap(Session.class);

        // STEP TWO : gather results from the postgres database
        Query<Employee> query = currentSession.createQuery("FROM rb_employees", Employee.class);

        return query.getResultList();
    }

    @Override
    public <S extends Employee> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    /**
     * Retrieve one employee from the postgreSQL database
     * @param integer the ID of the employee needed
     * @return either a copy of the employee, or a null pointer if the employee could not be found
     */
    @Override
    public Optional<Employee> findById(Integer integer) {
        // STEP ZERO : Loggin'
        Date date = new Date(System.currentTimeMillis());
        log.info("Retrieving employee {} at time {}", integer, date);

        // STEP ZERO.5 : Enter the try-catch to protect the database operation!
        try {
            // STEP ONE : Find it!
            Session currentSession = entityManager.unwrap(Session.class);
            Employee resultEmployee = new Employee();
            resultEmployee = currentSession.get(Employee.class, integer);
            if(resultEmployee.getUsername().isEmpty()) {
                throw new Exception("Employee not found!");
            } else {
                return Optional.of(resultEmployee);
            }
        } catch (Exception e) {
            log.error("ERROR finding employee in postgres database at time {}: {}", date, e);
            return Optional.empty();
        }
    }

    /**
     * Gets one employee, based on their username
     * @param username a String of the desired Employee's username
     * @return either a copy of the employee, or a null pointer if the employee could not be found
     */
    public Employee getByUsername(String username) {
        // STEP ZERO : Loggin'
        Date date = new Date(System.currentTimeMillis());
        log.info("Retrieving employee {} at time {}", username, date);

        // STEP ZERO.5 : Enter the try-catch to protect the database operation!
        try {
            // STEP ONE : Find it!
            Session currentSession = entityManager.unwrap(Session.class);
            Query query = currentSession.createQuery("SELECT * FROM rb_employees WHERE USERNAME =:username", Employee.class);
            query.setParameter("username", username);
            List<Employee> results = query.getResultList();
            if(results.isEmpty()) {
                throw new Exception("Employee not found!");
            } else {
                return results.get(0);
            }
        } catch (Exception e) {
            log.error("ERROR finding employee {} in postgres database at time {}: {}", username, date, e);
            return null;
        }
    }

    /**
     * Deletes a given employee from the database
     * @param id the id of the employee to delete
     * @return whether the delete was successful or not
     */
    public boolean deleteEmployee(Integer id) {
        boolean result = false;
        // STEP ZERO : Loggin'
        Date date = new Date(System.currentTimeMillis());
        log.info("Deleting employee {} at time {}", id, date);
        // STEP ONE : Confirm that the employee exists
        if(!findById(id).isPresent()) {
            log.error("Cannot delete employee {} as they do not exist", id);
            return false;
        } else {
            try {
                Session currentSession = entityManager.unwrap(Session.class);
                currentSession.delete(findById(id));
                //maybe make a query with the sql for deleting by id?
                //Query<Employee> query = currentSession.createQuery("DELETE FROM rb_employees WHERE EMPLOYEE_ID =:id"
                //query.setParameter("id", id);
                return true;
            } catch (Exception e) {
                log.error("Cannot delete employee {} because of exception: {}", id, e);
                return false;
            }
        }

    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Employee> findAll() {
        return null;
    }

    @Override
    public Iterable<Employee> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Employee entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Employee> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
