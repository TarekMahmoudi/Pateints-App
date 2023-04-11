package com.mahmoudi.patientproject.presentation.features.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudi.patientproject.domain.model.AddPatientRemoteModel
import com.mahmoudi.patientproject.domain.model.patient.PatientRemoteModule
import com.mahmoudi.patientproject.presentation.databinding.RowPatientBinding

class PatientsAdapter : ListAdapter<PatientRemoteModule, PatientsAdapter.PatientsViewHolder>(DiffCallback){


    var indexLastSelected = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val binding = RowPatientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientsViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        Log.d("TAG", "onBindViewHolder: $position")
        val model = getItem(position)
        holder.bind(model, position)

    }


    inner class PatientsViewHolder(private val binding: RowPatientBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: PatientRemoteModule, position: Int) {
            binding.model = model

            binding.cardView.setOnClickListener {
                if (position != indexLastSelected) {
                    //if not default
                    //notify last view item
                    if (indexLastSelected != -1) {
                        getItem(position).selected = false
                        notifyItemChanged(indexLastSelected)
                    }
                    // notify new item
                    indexLastSelected = position
                    getItem(position).selected = true
                    notifyItemChanged(position)
                }
            }

        }
    }
    private object DiffCallback : DiffUtil.ItemCallback<PatientRemoteModule>() {

        override fun areItemsTheSame(
            oldItem: PatientRemoteModule,
            newItem: PatientRemoteModule
        ): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(
            oldItem: PatientRemoteModule,
            newItem: PatientRemoteModule
        ): Boolean {
            return oldItem == newItem

        }
    }
}