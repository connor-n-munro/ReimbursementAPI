package controllers;

import model.Employee;
import model.Reimbursement;
import model.rbRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import services.EmployeeService;
import services.ReimbursementService;

import java.util.List;
import java.util.Optional;

public class ReimbursementController {

    private ReimbursementService reimbursementService;
    private EmployeeService employeeService;

    @Autowired
    public ReimbursementController(ReimbursementService r, EmployeeService e) {
        this.reimbursementService = r;
        this.employeeService = e;
    }

    @GetMapping("/manager/reimburse")
    public ResponseEntity getAllRequests() {
        List<Reimbursement> allReimbursements = reimbursementService.getAllRequests();
        if(allReimbursements.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(allReimbursements);
        }
    }

    @GetMapping("/manager/{id}/reimbursements")
    public ResponseEntity getAllById(@PathVariable("id") Long id) {
        Optional<Employee> manager = employeeService.getByID(Math.toIntExact(id));
        if(manager.isPresent() && manager.get().getRole() == rbRole.ROLE_MANAGER) {
            return ResponseEntity.ok().body(reimbursementService.findAllByManager(manager.get().getId()));
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
