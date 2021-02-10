package com.example.cabstwo.firebase;

import android.app.Service;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.cabstwo.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

class Myfirebasemessagging extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        getFirebaseMessage(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());

    }

    public void getFirebaseMessage(String title, String msg) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Firebasechannel")
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle(title)
                .setContentText(msg).setAutoCancel(true);
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(101, builder.build());
    }
}
