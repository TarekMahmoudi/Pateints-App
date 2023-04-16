package com.mahmoudi.patientproject.presentation.features.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudi.patientproject.domain.model.patient.PatientRemoteModule
import com.mahmoudi.patientproject.domain.usecase.details.GetPatientByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPatientByIdUseCase: GetPatientByIdUseCase,
    state: SavedStateHandle
):ViewModel(){

    private val _detailsStateFlow:MutableStateFlow<PatientRemoteModule?> = MutableStateFlow(null)
    val detailsStateFlow = _detailsStateFlow.asStateFlow()

    private val _detailsLoadingStateFlow:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val detailsLoadingStateFlow = _detailsLoadingStateFlow.asStateFlow()

    private val _detailsErrorStateFlow:MutableStateFlow<Exception?> = MutableStateFlow(null)
    val detailsErrorStateFlow = _detailsErrorStateFlow.asStateFlow()

    private val savedStateHandle = state

    init {
        details()
    }

     fun details() {
        val id = savedStateHandle.get<String>("id")?:"-1"
         viewModelScope.launch {
             _detailsLoadingStateFlow.emit(true)
             try{
                 _detailsStateFlow.emit(getPatientByIdUseCase(id))
             }catch (e:java.lang.Exception){
                 _detailsLoadingStateFlow.emit(false)
             }
         }
    }

}