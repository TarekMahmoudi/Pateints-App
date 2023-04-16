package com.mahmoudi.patientproject.presentation.features.patients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudi.patientproject.domain.model.patient.PatientRemoteModule
import com.mahmoudi.patientproject.data.repository.PatientsRepositoryImpl
import com.mahmoudi.patientproject.domain.model.delete.PatientDeleteResponseModel
import com.mahmoudi.patientproject.domain.usecase.GetPatientsSortedByNameUseCase
import com.mahmoudi.patientproject.domain.usecase.delete.DeletePatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor (
    private val getPatientsSortedByNameUseCase: GetPatientsSortedByNameUseCase,
    private val deletePatientUseCase: DeletePatientUseCase

) : ViewModel() {

     private val _patientsStateFlow : MutableStateFlow<List<PatientRemoteModule>> = MutableStateFlow(emptyList())
    val patientsStateFlow =_patientsStateFlow.asStateFlow()

    private val _patientsLoadingStateFlow : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val patientsLoadingStateFlow =_patientsLoadingStateFlow.asStateFlow()

    private val _patientsErrorFlow : MutableStateFlow <Exception?> = MutableStateFlow(null)
    val patientsErrorFlow = _patientsErrorFlow.asStateFlow()

    private val _deletePatientLiveData: MutableLiveData<PatientDeleteResponseModel> = MutableLiveData()
    val deletePatientsLiveData : LiveData<PatientDeleteResponseModel> = _deletePatientLiveData



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
    fun deletePatients(id :String){
        viewModelScope.launch {

            _patientsLoadingStateFlow.emit(true)

            try {
                _deletePatientLiveData.postValue(deletePatientUseCase(id)!!)
            }catch (e: Exception){
                _patientsErrorFlow.emit(e)

            }
            _patientsLoadingStateFlow.emit(false)

        }

    }

}