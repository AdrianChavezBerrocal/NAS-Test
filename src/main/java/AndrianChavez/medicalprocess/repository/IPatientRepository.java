package AndrianChavez.medicalprocess.repository;

import AndrianChavez.medicalprocess.entity.Patient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("patientRepository")
@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {

    Patient findPatientById(Long id);

    @Query(value = "SELECT p FROM Patient p WHERE p.active = true")
    List<Patient> findAllDoctor();
}

