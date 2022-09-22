package AndrianChavez.medicalprocess.repository;

import AndrianChavez.medicalprocess.entity.Doctor;
import AndrianChavez.medicalprocess.entity.Patient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Qualifier("doctorRepository")
@Repository
public interface IDoctorRepository extends JpaRepository<Doctor,Long>{

    Doctor findDoctorById(Long id);

    @Query(value = "SELECT d FROM Doctor d WHERE d.active = true")
    List<Doctor> findAllDoctor();

    @Query(value = "SELECT p FROM Patient p WHERE p.doctorid = ?1")
    List<Patient> findAllPatientOfDoctor(Long id);
}
