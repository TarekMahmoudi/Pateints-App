package com.mahmoudi.patientproject.domain.repository.patients

import com.mahmoudi.patientproject.domain.model.AddPatientRemoteModel
import com.mahmoudi.patientproject.domain.model.BodyAddPatientModel
import com.mahmoudi.patientproject.domain.model.patient.PatientRemoteModule
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


interface PatientsRepository  {

    suspend fun getPatients(): List<PatientRemoteModule>

    suspend fun addPatients(bodyAddPatientModel: BodyAddPatientModel):AddPatientRemoteModel

}