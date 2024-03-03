package com.example.fitnext;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
    private static final long QUOTE_UPDATE_INTERVAL = 10000; // Update every 10 seconds

    TextView textMotivationalQuote;
    private String[] motivationalQuotes = {
            "The only way to do great work is to love what you do. - Steve Jobs",
            "Don't watch the clock; do what it does. Keep going. - Sam Levenson",
            "The only way to do great work is to love what you do. - Steve Jobs",

            "Don't watch the clock; do what it does. Keep going. - Sam Levenson",

            "Believe you can and you're halfway there. - Theodore Roosevelt",

            "Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston Churchill",

            "Your time is limited, don't waste it living someone else's life. - Steve Jobs",

            "The only limit to our realization of tomorrow will be our doubts of today. - Franklin D. Roosevelt",

            "The only person you are destined to become is the person you decide to be. - Ralph Waldo Emerson",

            "Strive not to be a success, but rather to be of value. - Albert Einstein",

            "Success is stumbling from failure to failure with no loss of enthusiasm. - Winston S. Churchill",

            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "You are never too old to set another goal or to dream a new dream. - C.S. Lewis",

            "The only limit to our realization of tomorrow will be our doubts of today. - Franklin D. Roosevelt",

            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",

            "Do not wait to strike till the iron is hot, but make it hot by striking. - William Butler Yeats",

            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",

            "I find that the harder I work, the more luck I seem to have. - Thomas Jefferson",

            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",

            "It's not about how bad you want it, it's about how hard you're willing to work for it. - Unknown",

            "The only person you are destined to become is the person you decide to be. - Ralph Waldo Emerson",

            "The difference between a successful person and others is not a lack of strength, not a lack of knowledge, but rather a lack in will. - Vince Lombardi",
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
        Toast.makeText(getContext(), "6", Toast.LENGTH_SHORT).show();
        return view;
    }

    // Create notification channel
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel);
            Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
        }
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
        Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getContext(), "3", Toast.LENGTH_SHORT).show();
    }

    // Update the quote
    private void updateQuote() {
        // Get a random quote
        String randomQuote = getRandomQuote();
        textMotivationalQuote.setText(randomQuote);
        Toast.makeText(getContext(), "4", Toast.LENGTH_SHORT).show();
    }

    // Select a random quote from the array
    private String getRandomQuote() {
        Toast.makeText(getContext(), "5", Toast.LENGTH_SHORT).show();
        Random random = new Random();
        int index = random.nextInt(motivationalQuotes.length);
        return motivationalQuotes[index];

    }
}
