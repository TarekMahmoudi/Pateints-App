package com.mahmoudi.patientproject.domain.model.patient

data class PatientsWrapperRemoteModule (

    val status : Int,

    val message : String,

    val data: List<PatientRemoteModule>




    )
