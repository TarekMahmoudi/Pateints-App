package com.mahmoudi.patientproject.domain.model.details

import com.mahmoudi.patientproject.domain.model.patient.PatientRemoteModule

data class DetailsPatientWrappedRemoteModel(
    val status : Int,

    val message : String,

    val data: PatientRemoteModule
)
