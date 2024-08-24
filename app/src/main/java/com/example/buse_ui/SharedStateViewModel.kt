package com.example.buse_ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buse_ui.PresentationStudent.InAppNotifContent
import com.example.buse_ui.PresentationStudent.inAppNotifRepository
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import kotlinx.coroutines.launch

class SharedStateViewModel(
//    private val repository: inAppNotifRepository
) : ViewModel(){
    var selectedItemIndex by mutableIntStateOf(0)
    private val _notifications = MutableLiveData<List<InAppNotifContent>>()
    val notifications: LiveData<List<InAppNotifContent>> get() = _notifications

    fun addNotification(notification: InAppNotifContent) {
        val currentNotifications = _notifications.value?: emptyList()
        _notifications.value = currentNotifications + notification
    }

    fun removeNotification(index: Int) {
        _notifications.value?.let { currentNotifications ->
            if (index in currentNotifications.indices) {
                val updatedNotifications = currentNotifications.toMutableList()
                updatedNotifications.removeAt(index)
                _notifications.value = updatedNotifications
            }
        }
    }

//    fun addNotification(notification: InAppNotifContent) {
//        viewModelScope.launch {
//            repository.addNotification(InAppNotifContent(0, notification.sender, notification.message, notification.painter))
//        }
//    }
//
//    fun removeNotification(notification: InAppNotifContent) {
//        viewModelScope.launch {
//            repository.removeNotification(InAppNotifContent(0, notification.sender, notification.message, notification.painter))
//        }
//    }

}