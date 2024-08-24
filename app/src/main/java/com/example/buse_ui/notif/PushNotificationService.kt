package com.example.buse_ui.notif

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("FCM", token)
        // Update server
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.let {
            val title = it.title
            val body = it.body
            if (title != null) {
                if (body != null) {
                    NotificationUtils.showNotification(applicationContext, title, body)
                }
            }
            notifApp.notifViewModel.updateLaunchAnimate(true)
        }
    }
}