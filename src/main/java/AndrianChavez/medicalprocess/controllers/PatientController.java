package AndrianChavez.medicalprocess.controllers;

import AndrianChavez.medicalprocess.entity.Patient;
import AndrianChavez.medicalprocess.resource.PatientResource;
import AndrianChavez.medicalprocess.resource.SavePatientResource;
import AndrianChavez.medicalprocess.service.IPatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/getAllPatients")
    public List<PatientResource> getAllPatients(){
        List<Patient> patients = patientService.findAllPatient();
        if (patients == null) {
            return null;
        }
        List<PatientResource> patientResourceList = patients.stream().map(patient -> {
            return convertToResource(patient);
        }).collect(Collectors.toList());
        return patientResourceList;
    }

    @GetMapping(value = "/getPatientById/{id}")
    public PatientResource getPatientById(@PathVariable("id") Long id){
        Patient patient = patientService.findPatientById(id);
        if (patient == null) {
            return null;
        }
        return convertToResource(patient);
    }

    @PostMapping(value = "/createPatient")
    public PatientResource createPatient(@RequestBody SavePatientResource patient, @RequestParam Long doctorId){
        Patient patientCreated = patientService.createPatient(convertToEntity(patient), doctorId);
        return convertToResource(patientCreated);
    }

    @PutMapping(value = "/updatePatient/{id}")
    public PatientResource updatePatient(@PathVariable("id") Long id, @RequestBody SavePatientResource patient){
        Patient patientUpdated = patientService.updatePatient(id, convertToEntity(patient));
        return convertToResource(patientUpdated);
    }

    @DeleteMapping(value = "/deletePatient/{id}")
    public PatientResource deletePatient(@PathVariable("id") Long id){
        Patient patientDeleted = patientService.deletePatient(id);
        return convertToResource(patientDeleted);
    }

    private Patient convertToEntity(SavePatientResource resource) {
        return modelMapper.map(resource, Patient.class);
    }

    private PatientResource convertToResource(Patient entity) {
        return modelMapper.map(entity, PatientResource.class);
    }
}
