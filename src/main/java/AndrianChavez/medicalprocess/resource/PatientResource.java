package AndrianChavez.medicalprocess.resource;

import lombok.Data;

@Data
public class PatientResource {

    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private boolean active;

}
