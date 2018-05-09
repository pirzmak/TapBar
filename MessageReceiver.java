package com.example.user.tapbar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.user.tapbar.clientViewModel.MapsActivity;
import com.example.user.tapbar.ownerViewModel.OwnerActivity;
import com.example.user.tapbar.ownerViewModel.ReservationsListActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessageReceiver extends FirebaseMessagingService {
    private static final int REQUEST_CODE = 1;
    private static final int NOTIFICATION_ID = 6578;
    private static final String TAG = "MessageReceiver";

    public MessageReceiver() {
        super();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        final String title = remoteMessage.getData().get("title");
        final String message = remoteMessage.getData().get("body");
        final String type = remoteMessage.getData().get("type");

        Log.d(TAG, "Message receive");

        showNotifications(title, message, type);
    }

    private void showNotifications(String title, String msg, String type) {
        Intent i;
        if(type.equals("Owner")) {
            i = new Intent(this, ReservationsListActivity.class);
        } else {
            i = new Intent(this, MapsActivity.class);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE,
                i, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentText(msg)
                .setContentTitle(title)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .build();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, notification);
    }
}
