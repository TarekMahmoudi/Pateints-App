package com.mahmoudi.patientproject.presentation.features.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mahmoudi.patientproject.presentation.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsPatientFragment :Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel : DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.detailsStateFlow.collect{
                response ->
                if (response!=null)
                    Toast.makeText(
                        requireContext(),
                        "response:$response",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }

        //lifecycleScope.launch{
          //  viewModel.detailsLoadingStateFlow.collect{show ->
            //    binding.progressCircular.isVisible = show
            //}
//        }
        lifecycleScope.launch {
            viewModel.detailsErrorStateFlow.collect{response ->
                if (response!=null){
                    Log.d("TAG", response.toString())
                }
            }
        }
    }


}