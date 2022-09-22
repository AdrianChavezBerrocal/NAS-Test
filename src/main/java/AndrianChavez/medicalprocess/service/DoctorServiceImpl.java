package AndrianChavez.medicalprocess.service;

import AndrianChavez.medicalprocess.entity.Doctor;
import AndrianChavez.medicalprocess.entity.Patient;
import AndrianChavez.medicalprocess.repository.IDoctorRepository;
import AndrianChavez.medicalprocess.repository.IPatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    private IDoctorRepository doctorRepository;

    @Override
    public List<Doctor> findAllDoctor() {
        return doctorRepository.findAllDoctor();
    }

    @Override
    public List<Patient> findAllPatientOfDoctor(Long id) {
        return doctorRepository.findAllPatientOfDoctor(id);
    }


    @Override
    public Doctor findDoctorById(Long id) {
        Doctor doctor = doctorRepository.findDoctorById(id);
        if (doctor == null || doctor.isActive() == false) {
            throw new RuntimeException("Doctor not found");
        }
        return doctorRepository.findDoctorById(id);
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        Doctor newDoctor = new Doctor();
        newDoctor.setFirstName(doctor.getFirstName());
        newDoctor.setLastName(doctor.getLastName());
        newDoctor.setSpecialty(doctor.getSpecialty());
        newDoctor.setActive(true);
        return doctorRepository.save(newDoctor);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor doctorToUpdate = doctorRepository.findDoctorById(id);
        if (doctorToUpdate == null || doctorToUpdate.isActive() == false) {
            return null;
        }
        doctorToUpdate.setFirstName(doctor.getFirstName());
        doctorToUpdate.setLastName(doctor.getLastName());
        doctorToUpdate.setSpecialty(doctor.getSpecialty());
        return doctorRepository.save(doctorToUpdate);
    }

    @Override
    public Doctor deleteDoctor(Long id) {
        Doctor doctorToDelete = doctorRepository.findDoctorById(id);
        if (doctorToDelete == null || doctorToDelete.isActive() == false) {
            return null;
        }
        doctorToDelete.setActive(false);
        return doctorRepository.save(doctorToDelete);
    }
}
