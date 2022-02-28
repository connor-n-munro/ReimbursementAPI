package model;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * A model class to represent a requested reimbursement
 * status can be either "UNPROCESSED", "APPROVED", or "DENIED".
 */
@Entity
@ToString @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor @Table(name="rb_reimbursements")
public class Reimbursement {
    Date date;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RB_ID", nullable = false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RQ_ID", nullable = false)
    private Employee requester;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MA_ID", nullable = false)
    private Employee manager;

    @Column(name = "TIME_SUBMITTED", nullable = false)
    private Timestamp timeSubmitted;

    @Column(name = "AMOUNT", nullable = false)
    private double amount;

    @Column(name = "PURPOSE", nullable = false)
    private String purpose;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "TIME_RESOLVED", nullable = false)
    private Timestamp timeResolved;


    public Reimbursement(Employee requester, double amount, String purpose) {
        this.requester = requester;
        this.date = new Date(System.currentTimeMillis());
        this.timeSubmitted = new Timestamp(date.getTime());
        this.amount = amount;
        this.purpose = purpose;
        this.status = "UNPROCESSED";
        this.timeResolved = null;
    }
}
