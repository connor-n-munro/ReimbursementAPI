package controllers;

import dao.EmployeePostgresDAO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import services.EmployeeService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Slf4j @Controller @AllArgsConstructor @NoArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    EmployeeService services = new EmployeeService();
    Employee currentEmployee;

    @Autowired
    public EmployeeController(EmployeeService services) {
        this.services = services;
    }

    @PostConstruct //this is going to be run after any dependency injection happens - post the use of this class's constructor
    public void init() {
        log.info("Initializing Employee Controller - {}",
                LocalDateTime.now());
        /*Not sure what to do here yet*/
    }



    public Employee getOneByID(Integer id) {
        return new Employee();
    }

}
