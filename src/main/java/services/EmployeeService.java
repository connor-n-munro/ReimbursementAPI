package services;

import dao.EmployeePostgresDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Employee;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Provides services relating to the employee and manager classes, actinga as a go-between for the EmployeePostgresDAO and the Employee/ManagerController
 */
@Service
@NoArgsConstructor @Slf4j
public class EmployeeService {

    private EmployeePostgresDAO employees;

    @Autowired
    public EmployeeService(EmployeePostgresDAO dao) {
        this.employees = dao;
    }

    @PostConstruct
    public void init() {
        log.info("Initializing Employee Services - {}", LocalDateTime.now());

    }

    public Optional<Employee> getByID(Integer id) {
        return employees.findById(id);
    }

    public void save(@NotNull Employee employee) {
        employees.save(employee);
    }

    public List<Employee> getAll() { return employees.getAll(); }

    public Employee getByUsername(String u) { return employees.getByUsername(u); }

    public boolean deleteEmployee(Integer id) { return employees.deleteEmployee(id); }

}
