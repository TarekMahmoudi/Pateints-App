package com.mahmoudi.patientproject.presentation.features.patients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mahmoudi.patientproject.domain.model.delete.PatientDeleteResponseModel
import com.mahmoudi.patientproject.domain.model.patient.PatientRemoteModule
import com.mahmoudi.patientproject.presentation.R
import com.mahmoudi.patientproject.presentation.databinding.FragmentPatientsBinding
import com.mahmoudi.patientproject.presentation.features.adapters.PatientsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PatientsFragment: Fragment() {

    private lateinit var binding: FragmentPatientsBinding
    private val viewModel: PatientsViewModel by viewModels()
    private lateinit var adapter :PatientsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPatientsBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initObserver()
        initListener()
    }

    private fun initAdapter() {
        adapter = PatientsAdapter(::deletePatient ,::onClickItem)
        binding.recyclerView.adapter = adapter
    }

    private fun initListener() {
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.addPatientsFragment)
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getPatients()
            lifecycleScope.launch{
                delay(3000)
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }


    private fun initObserver(){
        lifecycleScope.launch {
            viewModel.patientsStateFlow.collect(::onSuccessPatients)

            }
            lifecycleScope.launch {
                viewModel.patientsLoadingStateFlow.collect { response ->
                    binding.progressBar.isVisible = response
                }
            }
            lifecycleScope.launch {
                viewModel.patientsErrorFlow.collect { response ->
                    if (response != null) {
                        Log.d("TAG00", response.toString())
                    }

                }
            }
                lifecycleScope.launch{
                    viewModel.deletePatientsLiveData.observe(viewLifecycleOwner,::onPatientDeletedSuccess)
                }

            }
    private fun onPatientDeletedSuccess(response: PatientDeleteResponseModel?){
        if(response!=null){
            Toast.makeText(requireContext(),response.message,Toast.LENGTH_LONG).show()
            viewModel.getPatients()
        }

    }



    private fun onSuccessPatients(response : List<PatientRemoteModule>?){
        if (response?.isNotEmpty()==true)
            adapter.submitList(response)

    }

    fun deletePatient(id : String){

        MaterialAlertDialogBuilder(requireContext())
            .setMessage("are you sure to delete this patient")
            .setNegativeButton("no"){ dialog, _ ->
                dialog.dismiss() }
            .setPositiveButton("yes"){dialog, _ ->
                viewModel.deletePatients(id)
                dialog.dismiss()
            }.show()
    }
    private fun onClickItem(id:String){
        findNavController().navigate(R.id.detailsPatientFragment, bundleOf("id" to id))
    }
    }






