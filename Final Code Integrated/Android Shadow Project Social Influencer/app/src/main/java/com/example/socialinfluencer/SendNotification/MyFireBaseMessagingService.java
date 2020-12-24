package com.example.socialinfluencer.SendNotification;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.socialinfluencer.Advertiser.Notification;
import com.example.socialinfluencer.MainActivity;
import com.example.socialinfluencer.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

//https://stackoverflow.com/questions/49828430/how-to-send-fcm-notification-from-android-using-retrofit
public class MyFireBaseMessagingService extends FirebaseMessagingService {
    String title,message;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
            super.onMessageReceived(remoteMessage);
            title=remoteMessage.getData().get("title");
            message=remoteMessage.getData().get("body");
            addNotification(message,title);
//            Toast.makeText(getApplicationContext(),"Notification Recived",Toast.LENGTH_SHORT).show();
//            Log.d("TAGG_NOTIFICATIO",title);
//        NotificationCompat.Builder builder =
//                new NotificationCompat.Builder(getApplicationContext())
//                        .setSmallIcon(R.drawable.appicon)
//                        .setContentTitle(title)
//                        .setContentText(message);
//        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(0, builder.build());
    }
    private void addNotification(String message, String title) {
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications",NotificationManager.IMPORTANCE_HIGH);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.appicon)
                .setTicker("Social Influencer")
                .setContentTitle(title)
                .setContentText(message)
                .setContentInfo("Info");


        notificationManager.notify(/*notification id*/1, notificationBuilder.build());


//        NotificationCompat.Builder builder =
//                (NotificationCompat.Builder) new NotificationCompat.Builder(this);
//        builder.setSmallIcon(R.drawable.appicon)
//                .setContentTitle(title)
//                .setContentText(message);
//
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(contentIntent);
//
//        // Add as notification
//        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(0, builder.build());
    }

}
