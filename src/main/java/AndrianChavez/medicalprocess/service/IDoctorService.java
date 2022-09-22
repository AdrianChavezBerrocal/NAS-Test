package AndrianChavez.medicalprocess.service;

import AndrianChavez.medicalprocess.entity.Doctor;
import AndrianChavez.medicalprocess.entity.Patient;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IDoctorService {

    List<Doctor> findAllDoctor();
    List<Patient> findAllPatientOfDoctor(Long id);
    Doctor findDoctorById(Long id);
    Doctor createDoctor(Doctor doctor);
    Doctor updateDoctor(Long id, Doctor doctor);
    Doctor deleteDoctor(Long id);

}
