package services;

import dao.ReimbursementPostgresDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Reimbursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j
@NoArgsConstructor
public class ReimbursementService {

    ReimbursementPostgresDAO reimbursements;
    EmployeeService employeeService;

    @Autowired
    ReimbursementService(ReimbursementPostgresDAO dao) {
        this.reimbursements = dao;
    }

    public List<Reimbursement> getAllRequests() {
        return reimbursements.findAll();
    }

    public void save(Reimbursement r) {
        reimbursements.save(r);
    }

    //get all by status
    public List<Reimbursement> findAllByStatus(String status) {
        return reimbursements.findAllByStatus(status);
    }
    //get all by requester
    public List<Reimbursement> findAllByRequester(Integer empID) {
        if(employeeService.getByID(empID).isPresent()) {
            return reimbursements.findAllByRequester(employeeService.getByID(empID).get());
        } else { return null; }
    }
    //get all by manager
    public List<Reimbursement> findAllByManager(Integer manID) {
        return reimbursements.findAllByManager(employeeService.getByID(manID).get());
    }

}
