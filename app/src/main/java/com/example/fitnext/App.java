package com.example.fitnext;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class App extends Application {
  public static final String CHANNEL_ID = "channel";

  @Override
  public void onCreate() {
    super.onCreate();

    createNotificationChannels();
  }

  private void createNotificationChannels() {
    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "MedManager notifications", NotificationManager.IMPORTANCE_HIGH);
    channel.setDescription("Medmanager notifications appear here");

    NotificationManager manager = getSystemService(NotificationManager.class);
    manager.createNotificationChannel(channel);
  }
}
