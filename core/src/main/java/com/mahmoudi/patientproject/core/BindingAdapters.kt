package com.mahmoudi.patientproject.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun ImageView.imageUrl(url : String?){
    load(url)
}