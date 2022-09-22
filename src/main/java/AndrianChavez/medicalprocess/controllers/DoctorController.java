package AndrianChavez.medicalprocess.controllers;

import AndrianChavez.medicalprocess.entity.Doctor;
import AndrianChavez.medicalprocess.entity.Patient;
import AndrianChavez.medicalprocess.resource.DoctorResource;
import AndrianChavez.medicalprocess.resource.PatientResource;
import AndrianChavez.medicalprocess.resource.SaveDoctorResource;
import AndrianChavez.medicalprocess.service.IDoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/createDoctor")
    public Doctor createDoctor(SaveDoctorResource doctor){
        Doctor doctorCreated = doctorService.createDoctor(convertToEntity(doctor));
        return doctorCreated;
    }

    @GetMapping(value = "/getAllPatientsOfDoctor")
    public List<PatientResource> getAllPatientsOfDoctor(@RequestParam Long id){
        List<Patient> patients = doctorService.findAllPatientOfDoctor(id);
        if (patients == null) {
            return null;
        }
        List<PatientResource> patientResourceList = patients.stream().map(patient -> {
            return convertToResource(patient);
        }).collect(Collectors.toList());
        return patientResourceList;
    }

    @GetMapping(value = "/getAllDoctors")
    public List<DoctorResource> getAllDoctors(){
        List<Doctor> doctors = doctorService.findAllDoctor();
        if (doctors == null) {
            return null;
        }
        List<DoctorResource> doctorResourceList = doctors.stream().map(doctor -> {
            return convertToResource(doctor);
        }).collect(Collectors.toList());
        return doctorResourceList;
    }

    @GetMapping(value = "/getDoctorById/{id}")
    public DoctorResource getDoctorById(@PathVariable("id") Long id){
        Doctor doctor = doctorService.findDoctorById(id);
        if (doctor == null) {
            return null;
        }
        return convertToResource(doctor);
    }

    @DeleteMapping(value = "/deleteDoctor/{id}")
    public Doctor deleteDoctor(@PathVariable("id") Long id){
        Doctor doctorDeleted = doctorService.deleteDoctor(id);
        return doctorDeleted;
    }

    @PutMapping(value = "/updateDoctor/{id}")
    public DoctorResource updateDoctor(@PathVariable("id") Long id, @RequestBody SaveDoctorResource doctor){
        Doctor doctorUpdated = doctorService.updateDoctor(id, convertToEntity(doctor));
        return convertToResource(doctorUpdated);
    }



    private Doctor convertToEntity(SaveDoctorResource resource) {
        return modelMapper.map(resource, Doctor.class);
    }

    private DoctorResource convertToResource(Doctor entity) {
        return modelMapper.map(entity, DoctorResource.class);
    }

    private Patient convertToEntity(PatientResource resource) {
        return modelMapper.map(resource, Patient.class);
    }

    private PatientResource convertToResource(Patient entity) {
        return modelMapper.map(entity, PatientResource.class);
    }
}
