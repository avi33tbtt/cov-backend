package com.covid.controller;

import com.covid.dto.PatientLocationDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.covid.dto.PatientInfoDto;
import com.covid.service.PatientInfoService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/api")
public class PatientInfoController {

    @Autowired
    PatientInfoService patientService;

    @GetMapping("/getPatientInfo")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public PatientInfoDto getPatientForLocation(@RequestParam long patientId) {
        return patientService.getPatientForLocation(patientId);
    }

    @GetMapping("/searchPatient")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public PatientLocationDto searchPatient(@RequestParam(required = false) Long locationId,
            @RequestParam(required = false) Long healthProId, @RequestParam(required = false) String phoneNumber,
            @RequestParam(defaultValue = "100") int size, @RequestParam(defaultValue = "0") int from,
            @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String covid19Status,
            @RequestParam(required = false) String quarantineStatus,
            @RequestParam(required = false) String isolationStatus,
            @RequestParam(required = false) String quarantineRequestStatus,
            @RequestParam(required = false) String medicalRequestStatus,
            @RequestParam(required = false) String suppliesRequestStatus,
            @RequestParam(required = false) String geofenceStatus,
            @RequestParam(required = false) String heartbeatStatus,
            @RequestParam(required = false) String healthStatusAlert) {

        if (locationId != null || healthProId != null) {
            //Do Nothing
        }
        else if (StringUtils.isBlank(phoneNumber)) {
            throw new RuntimeException("PHONE_NUMBER_MANDATORY");
        }
        return patientService.searchPatients(locationId, healthProId, phoneNumber, size, from, firstName, lastName,
                covid19Status, quarantineStatus, isolationStatus, quarantineRequestStatus, medicalRequestStatus,
                suppliesRequestStatus, geofenceStatus, heartbeatStatus, healthStatusAlert);
    }
}
