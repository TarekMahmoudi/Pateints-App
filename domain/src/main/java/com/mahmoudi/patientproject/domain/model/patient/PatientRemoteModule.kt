package com.mahmoudi.patientproject.domain.model.patient

import com.google.gson.annotations.SerializedName

data class PatientRemoteModule (
    val condition : String,
    @SerializedName("_id")
    val id : String,
    @SerializedName("name")
    val namePatient : String,
    val address : String,
    val mobile : String,
    val email : String,
    val birthdate : String,
    val gender : String,
    val photo : String,
    val test : List<TestModel>,
    //local var
    var selected: Boolean = false
)
