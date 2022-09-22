package AndrianChavez.medicalprocess.resource;

import lombok.Data;

@Data
public class DoctorResource {
    private Long id;
    private String firstName;
    private String lastName;
    private String Specialty;
    private boolean active;
}
