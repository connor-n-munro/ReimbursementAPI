package controllers;

import lombok.extern.slf4j.Slf4j;
import model.Employee;
import model.Reimbursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.EmployeeService;
import services.ReimbursementService;

import java.util.Date;

@Controller @Slf4j
@RequestMapping("/manager")
public class ManagerController {
    private ReimbursementService reimbursementService;
    private EmployeeService employeeService;
    private Employee currentManager;

    /**
     * the constructor for the ManagerController class - uses the @Autowired annotation to ensure dependency injection happens automatically when the @SpringBootApplication method is called
     * @param r the ReimbursementService to be injected
     * @param e the EmployeeService to be injected
     */
    @Autowired
    public ManagerController(ReimbursementService r, EmployeeService e) {
        this.reimbursementService = r;
        this.employeeService = e;
    }

    /**
     * Reassigns a request from the current manager assigned to it - UPDATE
     * @param request the reimbursement request to be reassigned
     * @param requestID the ID of the reimbursement request to be reassigned
     * @return a redirect back to the manager homepage
     */
    @PostMapping("/reassign")
    public String reassignRequest(@ModelAttribute("reassignRequest") Reimbursement request, @RequestParam("requestID") int requestID) {
        // STEP ZERO : Logging
        Date date = new Date(System.currentTimeMillis());
        log.info("Reassigment of reimbursement request {} by manager {} to manager {} at {}", requestID, "CURRENT_MANAGER", "NEW_MANAGER", date);
        // STEP ONE : ensure the current user is authorized
        Employee manager = null;
        if(employeeService.getByID(request.getManager().getId()).isPresent()) {
            manager = employeeService.getByID(request.getManager().getId()).get();
        } else {
            log.error("This employee is not authorized to reassign reimbursement requests!");
            return null;
        }
        // STEP TWO : ensure that both the request being reassigned and the manager to whom it is being reassigned exist

        // STEP THREE : Reassign if step two passes

        // STEP FOUR : send em right on back to the homepage
        return "/manager";
    }

    /**
     * Returns a list of every reimbursement request in the dao
     * @param model the spring model where components are stored for access by the entire application
     * @return a redirect back to the manager homepage
     */
    @PostMapping("/listAll")
    public String listAllReimbursements(Model model) {

        //STEP ZERO : Loggin'
        Date date = new Date(System.currentTimeMillis());
        log.info("Listing all reimbursements for manager {} at time {}", "CURRENT_MANAGER", date);
        //STEP TWO : make sure the manager making the request exists
        if(employeeService.getByID(3).isPresent() && employeeService.getByID(0).get().getRole().equals("MANAGER")) {
            // STEP THREE :
        }



        return "/manager";
    }
}
