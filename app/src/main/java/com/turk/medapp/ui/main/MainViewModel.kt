package com.turk.medapp.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turk.medapp.business.GetMedicinesListUseCase
import com.turk.medapp.data.dtos.Medicine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getMedicinesListUseCase: GetMedicinesListUseCase) :
    ViewModel() {


    var userName: String = ""

    fun saveUserName(name: String) {
        userName = name
    }

    private val _medicinesList = MutableStateFlow<List<Medicine>>(emptyList())
    val medicinesList = _medicinesList.asStateFlow()

    private val _progressLoading = MutableStateFlow(false)
    val progressLoading = _progressLoading.asStateFlow()

    fun showLoading(show: Boolean) {
        _progressLoading.value = show
    }

    fun fetchMedicinesListUseCase() {

        viewModelScope.launch {
            showLoading(true)
            withContext(Dispatchers.IO) {
                getMedicinesListUseCase().collect {
                    it.either({
                        showLoading(false)

                    }, { medicines ->
                        showLoading(false)
                        _medicinesList.value = medicines
                    })
                }
            }
        }
    }

}