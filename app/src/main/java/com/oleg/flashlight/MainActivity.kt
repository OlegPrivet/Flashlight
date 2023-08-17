package com.oleg.flashlight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModelProvider
import com.oleg.flashlight.manager.FlashlightManager
import com.oleg.flashlight.ui.theme.FlashlightTheme
import com.oleg.flashlight.viewmodel.MainViewModel
import com.oleg.flashlight.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlashlightTheme {
                val viewModelFactory = MainViewModelFactory(FlashlightManager.getInstance(this))
                val viewModel: MainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

                val state by viewModel.state.collectAsState()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                viewModel.switchFlash()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        val image = when (state.isLight) {
                            true -> R.drawable.flash_on
                            else -> R.drawable.flash_off
                        }
                        Image(painter = painterResource(id = image), contentDescription = "flashlight")
                    }
                }
            }
        }
    }
}

