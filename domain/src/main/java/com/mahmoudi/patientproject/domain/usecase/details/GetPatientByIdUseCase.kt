package com.mahmoudi.patientproject.domain.usecase.details

import com.mahmoudi.patientproject.domain.model.patient.PatientRemoteModule
import com.mahmoudi.patientproject.domain.repository.patients.PatientsRepository
import javax.inject.Inject

class GetPatientByIdUseCase @Inject constructor(private val repository: PatientsRepository) {

    suspend operator fun invoke(id : String): PatientRemoteModule{
        return  repository.getPatientById(id)
    }
}