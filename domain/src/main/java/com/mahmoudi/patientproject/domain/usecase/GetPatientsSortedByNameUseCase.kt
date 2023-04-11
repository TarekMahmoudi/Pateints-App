package com.mahmoudi.patientproject.domain.usecase

import com.mahmoudi.patientproject.domain.model.patient.PatientRemoteModule
import com.mahmoudi.patientproject.domain.repository.patients.PatientsRepository
import javax.inject.Inject

class GetPatientsSortedByNameUseCase @Inject constructor(private val repository: PatientsRepository) {

    suspend operator fun invoke(): List<PatientRemoteModule>{
        return  repository.getPatients().sortedBy { it.namePatient}
    }
}