package com.oleg.flashlight.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.action.ActionCallback
import com.oleg.flashlight.manager.FlashlightManager

class RefreshAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters,
    ) {
        val flashlightManager = FlashlightManager.getInstance(context)
        flashlightManager.switchFlash(!flashlightManager.flashIsOn)
        val manager = GlanceAppWidgetManager(context)
        val widget = FlashLightWidget()
        val glanceIds = manager.getGlanceIds(widget.javaClass)
        glanceIds.forEach { glanceId ->
            widget.update(context, glanceId)
        }
    }
}