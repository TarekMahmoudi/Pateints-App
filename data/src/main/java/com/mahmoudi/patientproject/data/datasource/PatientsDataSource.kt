package com.mahmoudi.patientproject.data.datasource

import com.mahmoudi.patientproject.domain.model.AddPatientRemoteModel
import com.mahmoudi.patientproject.domain.model.BodyAddPatientModel
import com.mahmoudi.patientproject.domain.model.delete.PatientDeleteResponseModel
import com.mahmoudi.patientproject.domain.model.details.DetailsPatientWrappedRemoteModel
import com.mahmoudi.patientproject.domain.model.patient.PatientsWrapperRemoteModule
import retrofit2.http.*


interface PatientsDataSource {
    @GET ("patients")
    suspend fun getPatients(): PatientsWrapperRemoteModule

    @POST ("patients")
    suspend fun addPatient(@Body bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel

    @DELETE ("patients/{id}")
    suspend fun deletePatient(@Path("id") id : String ): PatientDeleteResponseModel

    @GET ("patients/{id}")
    suspend fun getPatientById(@Path("id")id : String): DetailsPatientWrappedRemoteModel
}