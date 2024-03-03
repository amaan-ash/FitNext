package com.example.fitnext;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.fitnext.R;

import java.util.Random;

public class QuotesFragment extends Fragment {

    private Handler handler = new Handler(Looper.getMainLooper());
    private static final long QUOTE_UPDATE_INTERVAL = 10800000 ; // Update every 3 hours

    TextView textMotivationalQuote;
    private String[] motivationalQuotes = {
            "The only way to do great work is to love what you do.",
            "Don't watch the clock; do what it does. Keep going.",
            "The only way to do great work is to love what you do.",

            "Don't watch the clock; do what it does. Keep going.",

            "Believe you can and you're halfway there.",

            "Your time is limited, don't waste it living someone else's life.",

            "Strive not to be a success, but rather to be of value.",

            "Success is stumbling from failure to failure with no loss of enthusiasm.",

            "The future belongs to those who believe in the beauty of their dreams.",
            "You are never too old to set another goal or to dream a new dream.",


            "Success usually comes to those who are too busy to be looking for it.",

            "Do not wait to strike till the iron is hot, but make it hot by striking.",


            "I find that the harder I work, the more luck I seem to have.",



            "It's not about how bad you want it, it's about how hard you're willing to work for it.",

            "The only person you are destined to become is the person you decide to be.",


    };

    // Notification channel ID
    private static final String CHANNEL_ID = "motivational_quotes_channel";

    // Notification ID
    private static final int NOTIFICATION_ID = 1;

    // Notification manager
    private NotificationManager notificationManager;

    public QuotesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quotes, container, false);
        textMotivationalQuote = view.findViewById(R.id.textMotivationalQuote);

        // Initialize notification manager
        notificationManager = (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel();

        // to display the quote for the first time when the fragment is opened for the first time
        updateQuote();

        // periodically update the quote
        scheduleQuoteUpdates();
        Log.d("6","6");
        return view;
    }

    // Create notification channel
    private void createNotificationChannel() {
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        // Register the channel with the system
        notificationManager.createNotificationChannel(channel);
     Log.d("1","1");
    }

    // Method to send notification
    private void sendNotification(String quote) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.fitnextapplogo)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(quote)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Show the notification
        notificationManager.notify(NOTIFICATION_ID, builder.build());
       Log.d("2","2");
    }

    // Method to update quote and send notification
    private void updateQuoteAndSendNotification() {
        String randomQuote = getRandomQuote();
        sendNotification(randomQuote);
    }

    // Periodically update the quote
    private void scheduleQuoteUpdates() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateQuoteAndSendNotification();
                // Schedule the next update
                handler.postDelayed(this, QUOTE_UPDATE_INTERVAL);
            }
        }, QUOTE_UPDATE_INTERVAL);
      Log.d("3","3");
    }

    // Update the quote
    private void updateQuote() {
        // Get a random quote
        String randomQuote = getRandomQuote();
        textMotivationalQuote.setText(randomQuote);
     Log.d("4","4");
    }

    // Select a random quote from the array
    private String getRandomQuote() {
     Log.d("5","5");
        Random random = new Random();
        int index = random.nextInt(motivationalQuotes.length);
        return motivationalQuotes[index];

    }
}
