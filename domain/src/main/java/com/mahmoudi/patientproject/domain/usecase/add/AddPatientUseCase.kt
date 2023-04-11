package com.mahmoudi.patientproject.domain.usecase.add

import com.mahmoudi.patientproject.domain.model.AddPatientRemoteModel
import com.mahmoudi.patientproject.domain.model.BodyAddPatientModel
import com.mahmoudi.patientproject.domain.repository.patients.PatientsRepository
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repository: PatientsRepository) {
    suspend operator fun invoke(bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel {
        return repository.addPatients(bodyAddPatientModel)

    }
}