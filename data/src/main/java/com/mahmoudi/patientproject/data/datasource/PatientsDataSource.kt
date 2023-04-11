package com.mahmoudi.patientproject.data.datasource

import com.mahmoudi.patientproject.domain.model.AddPatientRemoteModel
import com.mahmoudi.patientproject.domain.model.BodyAddPatientModel
import com.mahmoudi.patientproject.domain.model.patient.PatientsWrapperRemoteModule
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface PatientsDataSource {
    @GET ("patients")
    suspend fun getPatients(): PatientsWrapperRemoteModule

    @POST ("patients")
    suspend fun addPatient(@Body bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel
}