package com.example.birthdaywishing;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

public class AlarmBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String text = bundle.getString("event");
        String date = bundle.getString("date") + " " + bundle.getString("time");

        // Click on Notification
        Intent intent1 = new Intent(context, NotificationMessage.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.putExtra("message", text);

        PendingIntent pendingSwitchIntent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingSwitchIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
        }
        else{
            pendingSwitchIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT );

        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "notify_001");

        // Set all the properties for the notification
        mBuilder.setSmallIcon(R.drawable.alarm);
        mBuilder.setContentTitle("Reminder");
        mBuilder.setContentText(text);
        mBuilder.setContentInfo(date);
        mBuilder.setAutoCancel(true);
        mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        mBuilder.setOnlyAlertOnce(true);
        mBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        mBuilder.setContentIntent(pendingSwitchIntent);

        // Create a notification channel after API level 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "channel_id";
            NotificationChannel channel = new NotificationChannel(channelId, "Channel Name", NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        Notification notification = mBuilder.build();
        notificationManager.notify(1, notification);
    }
}
