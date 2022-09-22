package AndrianChavez.medicalprocess.service;

import AndrianChavez.medicalprocess.entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPatientService {

    List<Patient> findAllPatient();
    Patient findPatientById(Long id);
    Patient createPatient(Patient patient, Long doctorId);
    Patient updatePatient(Long id, Patient patient);
    Patient deletePatient(Long id);
}
