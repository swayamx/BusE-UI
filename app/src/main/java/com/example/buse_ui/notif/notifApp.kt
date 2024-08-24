package com.example.buse_ui.notif

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class notifApp:Application(){
    companion object {
        lateinit var notifViewModel: notifViewModel
    }

    override fun onCreate() {
        super.onCreate()
        notifViewModel = notifViewModel()
    }
}