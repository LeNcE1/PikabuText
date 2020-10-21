package com.example.pikabutext.core.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T, OT> LiveData<T>.map(function: (T) -> OT): LiveData<OT> {
    val mutableLiveData = MutableLiveData<OT>()
    observeForever { mutableLiveData.value = function(it) }
    return mutableLiveData
}

fun <T> MutableLiveData<T>.readOnly(): LiveData<T> = this