package com.oleg.flashlight.manager

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Handler
import android.os.Looper

class FlashlightManager private constructor(context: Context) : Any() {

    private var cameraManager: CameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    private lateinit var cameraId: String
    var torchCallback: ((Boolean) -> Unit)? = null
    var flashIsOn = false

    init {
        try {
            cameraId = cameraManager.cameraIdList[0]
            cameraManager.registerTorchCallback(
                object : CameraManager.TorchCallback() {
                    override fun onTorchModeChanged(cameraId: String, enabled: Boolean) {
                        torchCallback?.let {
                            flashIsOn = enabled
                            it(enabled)
                        }

                    }
                },
                Handler(Looper.getMainLooper())
            )
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    fun switchFlash(isLight: Boolean) {
        cameraManager.setTorchMode(cameraId, isLight)
    }

    companion object {
        private var instance: FlashlightManager? = null
        @Synchronized
        fun getInstance(context: Context): FlashlightManager {
            if (instance == null) {
                instance = FlashlightManager(context)
            }
            return instance!!
        }
    }
}