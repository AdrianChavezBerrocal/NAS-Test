package AndrianChavez.medicalprocess.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_patient")
@Data
public class Patient {
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
    @Column(name = "dni",length = 200)
    private String dni;

    @NotNull
    @Column(name = "doctor")
    private Long doctorid;

    @Column(name = "active", length = 100)
    private boolean active;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

}
