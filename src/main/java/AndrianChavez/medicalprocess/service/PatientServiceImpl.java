package AndrianChavez.medicalprocess.service;


import AndrianChavez.medicalprocess.entity.Patient;
import AndrianChavez.medicalprocess.repository.IDoctorRepository;
import AndrianChavez.medicalprocess.repository.IPatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService {

    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    private IDoctorRepository doctorRepository;

    @Override
    public List<Patient> findAllPatient() {
        return patientRepository.findAllDoctor();
    }

    @Override
    public Patient findPatientById(Long id) {
        Patient patient = patientRepository.findPatientById(id);
        if (patient == null || patient.isActive() == false) {
            throw new RuntimeException("Patient not found");
        }
        return patientRepository.findPatientById(id);
    }

    @Override
    public Patient createPatient(Patient patient, Long doctorId) {
        var existingDoctor = doctorRepository.findDoctorById(doctorId);
        if (existingDoctor == null || existingDoctor.isActive() == false) {
            throw new RuntimeException("Doctor not found");
        }
        Patient newPatient = new Patient();
        newPatient.setFirstName(patient.getFirstName());
        newPatient.setLastName(patient.getLastName());
        newPatient.setDni(patient.getDni());
        newPatient.setDoctorid(doctorId);
        newPatient.setActive(true);
        return patientRepository.save(newPatient);
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) {
        Patient patientToUpdate = patientRepository.findPatientById(id);
        if (patientToUpdate == null || patientToUpdate.isActive() == false) {
            return null;
        }
        patientToUpdate.setFirstName(patient.getFirstName());
        patientToUpdate.setLastName(patient.getLastName());
        patientToUpdate.setDni(patient.getDni());
        return patientRepository.save(patientToUpdate);
    }

    @Override
    public Patient deletePatient(Long id) {
        Patient patientToDelete = patientRepository.findPatientById(id);
        if (patientToDelete == null || patientToDelete.isActive() == false) {
            return null;
        }
        patientToDelete.setActive(false);
        return patientRepository.save(patientToDelete);
    }
}
