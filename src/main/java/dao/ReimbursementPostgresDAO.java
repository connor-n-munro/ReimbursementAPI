package dao;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Employee;
import model.Reimbursement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository @Slf4j
@NoArgsConstructor
public class ReimbursementPostgresDAO {

    private EntityManager entityManager;

    @Query(value="SELECT * FROM rb_reimbursements WHERE RQ_ID = ?1", nativeQuery = true)
    public List<Reimbursement> getAllByID(Integer id) {
        Query query;
        List<Reimbursement> list = new ArrayList<Reimbursement>();
        return list;
    }

    @Query(value="SELECT * FROM rb_reimbursements", nativeQuery = true)
    public List<Reimbursement> findAll() {
        List<Reimbursement> list = new ArrayList<Reimbursement>();
        return list;
    }

    public void save(Reimbursement r) {

    }
    @Query(value="SELECT * FROM rb_reimbursements WHERE STATUS = ?1", nativeQuery = true)
    public List<Reimbursement> findAllByStatus(String status) {
        List<Reimbursement> list = new ArrayList<Reimbursement>();
        return list;
    }

    @Query(value="SELECT * FROM rb_reimbursements WHERE MA_ID = ?1", nativeQuery = true)
    public List<Reimbursement> findAllByManager(Employee manager) {
        List<Reimbursement> list = new ArrayList<Reimbursement>();
        return list;
    }

    @Query(value="SELECT * FROM rb_reimbursements WHERE RQ_ID = ?1", nativeQuery = true)
    public List<Reimbursement> findAllByRequester(Employee requester) {
        List<Reimbursement> list = new ArrayList<Reimbursement>();
        return list;
    }

}
