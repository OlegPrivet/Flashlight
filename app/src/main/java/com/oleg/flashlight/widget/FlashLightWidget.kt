package com.oleg.flashlight.widget

import android.content.Context
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.size
import com.oleg.flashlight.manager.FlashlightManager
import com.oleg.flashlight.R

class FlashLightWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val flashlightManager = FlashlightManager.getInstance(context)
            SideEffect {
                println("Flash is on: ${flashlightManager.flashIsOn}")
            }
            val imageRes = when (flashlightManager.flashIsOn) {
                true -> R.drawable.flash_on
                else -> R.drawable.flash_off
            }
            Column(
                modifier = GlanceModifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = GlanceModifier.size(70.dp).padding(bottom = 10.dp).clickable(
                        onClick = actionRunCallback<RefreshAction>()
                    ),
                    provider = ImageProvider(imageRes),
                    contentDescription = "flashlight"
                )
            }
        }
    }
}