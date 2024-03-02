package com.example.fitnext;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;



public class AlarmBroadcastReceiver extends BroadcastReceiver {
    public static final String CHANNEL_ID = "MedicineReminderChannel";
    private NotificationChannel serviceChannel;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String medName = bundle.getString("medName");
        String medQty = bundle.getString("medQty");
        String userName = bundle.getString("userName");

        Intent intentTo = new Intent(context, AlarmActivity.class);
        intentTo.putExtra("medName", medName);
        intentTo.putExtra("medQty", medQty);
        intentTo.putExtra("userName", userName);
        intentTo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentTo, PendingIntent.FLAG_IMMUTABLE);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.ic_baseline_add_alarm_24)
                .setContentTitle("Medicine Reminder")
                .setContentText(userName + ", please take " + medQty + " dose of " + medName + ".")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .build().flags = Notification.FLAG_NO_CLEAR | Notification.PRIORITY_HIGH;

        serviceChannel = new NotificationChannel(
                CHANNEL_ID,
                "Medicine Reminder Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
        );
        notificationManager.createNotificationChannel(serviceChannel);
        mBuilder.setChannelId(CHANNEL_ID);

        Notification notification = mBuilder.build();
        notificationManager.notify(0, notification);

        MediaPlayer player = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        player.start();
    }
}
