package athletes.athletes.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "athletes")
public class AthletesEntity implements Serializable {
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "country")
    private String country;

    @Column(name = "date_of_birth")
    private Date dataOfBirth;

    @Column(name = "created_date_time", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdDate;

    @Column(name = "athletes_created_by")
    private String createdBy;

    @Column(name = "athletes_modified_date_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp modifiedDate;

    @Column(name = "athletes_modified_by")
    private String modifiedBy;

    @OneToMany(mappedBy = "athletes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AthletesEventEntity> events = new ArrayList<AthletesEventEntity>();

    @Column(name = "status")
    private String status;

}
