package com.oleg.flashlight.viewmodel

import androidx.lifecycle.ViewModel
import com.oleg.flashlight.manager.FlashlightManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(
    private val manager: FlashlightManager,
) : ViewModel() {

    private val _state = MutableStateFlow(MainViewState(isLight = false))
    val state = _state.asStateFlow()

    init {
        manager.torchCallback = {
            _state.update { currentState ->
                currentState.copy(isLight = it)
            }
        }
    }

    fun switchFlash() {
        _state.update { currentState ->
            currentState.copy(isLight = !currentState.isLight)
        }
        manager.switchFlash(_state.value.isLight)
    }
}