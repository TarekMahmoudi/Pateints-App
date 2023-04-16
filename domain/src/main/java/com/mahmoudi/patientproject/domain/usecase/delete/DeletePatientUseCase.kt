package com.mahmoudi.patientproject.domain.usecase.delete
import com.mahmoudi.patientproject.domain.model.delete.PatientDeleteResponseModel
import com.mahmoudi.patientproject.domain.repository.patients.PatientsRepository
import javax.inject.Inject


    class DeletePatientUseCase @Inject constructor(private val repository: PatientsRepository) {

        suspend operator fun invoke(id : String): PatientDeleteResponseModel {
            return repository.deletePatients(id)

        }
}