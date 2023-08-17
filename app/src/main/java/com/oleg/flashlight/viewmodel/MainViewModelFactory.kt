package com.oleg.flashlight.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oleg.flashlight.manager.FlashlightManager

class MainViewModelFactory(private val manager: FlashlightManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(manager) as T
    }
}