package AndrianChavez.medicalprocess.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "tbl_doctor")
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "firstName",length = 200)
    private String firstName;

    @NotNull
    @Column( name = "lastName",length = 200)
    private String lastName;

    @NotNull
    @Column(name = "specialty", length = 100)
    private String Specialty;

    @Column(name = "active", length = 100)
    private boolean active;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Patient> patients;
}
