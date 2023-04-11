package com.mahmoudi.patientproject.data.repository

import com.mahmoudi.patientproject.data.datasource.PatientsDataSource
import com.mahmoudi.patientproject.domain.model.AddPatientRemoteModel
import com.mahmoudi.patientproject.domain.model.BodyAddPatientModel
import com.mahmoudi.patientproject.domain.model.patient.PatientRemoteModule
import com.mahmoudi.patientproject.domain.repository.patients.PatientsRepository
import javax.inject.Inject

class PatientsRepositoryImpl @Inject constructor(private val patientsDataSource : PatientsDataSource) :
    PatientsRepository {

    override suspend fun getPatients(): List<PatientRemoteModule> {
        return patientsDataSource.getPatients().data.sortedBy { it.namePatient }
    }

    override suspend fun addPatients(bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel {
        return patientsDataSource.addPatient(bodyAddPatientModel)
    }
}