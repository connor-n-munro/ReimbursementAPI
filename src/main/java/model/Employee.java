package model;

import lombok.*;
import javax.persistence.*;

/**
 * A model class to represent an employee - either seeking reimbursements or to resolve them.
 */
@Entity @ToString
@NoArgsConstructor @Getter @Setter
@AllArgsConstructor @Table(name="rb_employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID", nullable = false)
    private Integer id;

    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;

    @Column(name = "HASH", nullable = false)
    private String hash;

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private rbRole role;

}
