package com.mahmoudi.patientproject.presentation.features.patients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudi.patientproject.domain.model.patient.PatientRemoteModule
import com.mahmoudi.patientproject.data.repository.PatientsRepositoryImpl
import com.mahmoudi.patientproject.domain.usecase.GetPatientsSortedByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor (
    private val getPatientsSortedByNameUseCase: GetPatientsSortedByNameUseCase
    ) : ViewModel() {

     private val _patientsStateFlow : MutableStateFlow<List<PatientRemoteModule>> = MutableStateFlow(emptyList())
    val patientsStateFlow =_patientsStateFlow.asStateFlow()

    private val _patientsLoadingStateFlow : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val patientsLoadingStateFlow =_patientsLoadingStateFlow.asStateFlow()

    private val _patientsErrorFlow : MutableStateFlow <Exception?> = MutableStateFlow(null)
    val patientsErrorFlow = _patientsErrorFlow.asStateFlow()



    init {
        getPatients()
    }

     fun getPatients(){
        viewModelScope.launch {

            _patientsLoadingStateFlow.emit(true)

            try {
                _patientsStateFlow.emit(getPatientsSortedByNameUseCase())
            }catch (e: Exception){
                _patientsErrorFlow.emit(e)

            }
            _patientsLoadingStateFlow.emit(false)

        }

    }

}