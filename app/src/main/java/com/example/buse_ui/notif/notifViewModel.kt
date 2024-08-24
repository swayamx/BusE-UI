package com.example.buse_ui.notif

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class notifViewModel:ViewModel() {
    private val _launchAnimate = MutableStateFlow(false)
    val launchAnimate = _launchAnimate.asStateFlow()

    fun updateLaunchAnimate(value: Boolean){
        Log.d("FCM", "updateing launch $value")
        _launchAnimate.update {
            value
        }
    }
}