package com.example.fitnext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;

public class ArticleFragment extends Fragment {

    ListView listView;
    ArrayAdapter<String> adapter;
    HashMap<String, String> itemToLinkMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article, container, false);

        listView = rootView.findViewById(R.id.listView);

        // Sample data for the ListView
        String[] items = {
                "How to Prevent Diabetes?",
                "How to Get Relief From Headache?",
                "Why Health is Wealth?",
                "How can I lose weight effectively?",
                "What are the benefits of regular exercise?",
                "How can I improve my cardiovascular health?",
                "What are some low-impact exercises for beginners?",
                "What are the best foods for muscle recovery after a workout?",
                "What are some tips for staying hydrated during workouts?",
                "How can I manage stress through exercise and relaxation techniques?",
                "What are the benefits of strength training for women?",
                "What are some effective ways to increase metabolism?",
                "What are the dangers of overtraining?",
                "How can I create a balanced workout routine?",
                "What are some healthy snacks for on-the-go energy?",
                "How can I prevent injuries during exercise?",
                "What are the health benefits of yoga and meditation?",
                "How can I improve my sleep quality for better recovery?",
                "What are the recommended guidelines for physical activity for adults?"
        };


        // Sample links for the items
        itemToLinkMap = new HashMap<>();
        itemToLinkMap.put("How to Prevent Diabetes?", "https://www.mayoclinic.org/diseases-conditions/type-2-diabetes/in-depth/diabetes-prevention/art-20047639");
        itemToLinkMap.put("How to Get Relief From Headache?", "https://www.medicalnewstoday.com/articles/323992");
        itemToLinkMap.put("Why Health is Wealth?", "https://powerforwardgroup.com/blog/health-is-wealth");
        itemToLinkMap.put("How can I lose weight effectively?", "https://www.medicalnewstoday.com/articles/322345");
        itemToLinkMap.put("What are the benefits of regular exercise?", "https://www.mayoclinic.org/healthy-lifestyle/fitness/in-depth/exercise/art-20048389");
        itemToLinkMap.put("How can I improve my cardiovascular health?", "https://www.health.harvard.edu/healthbeat/10-small-steps-for-better-heart-health");
        itemToLinkMap.put("What are some low-impact exercises for beginners?", "https://www.shape.com/low-impact-workouts-6544218");
        itemToLinkMap.put("What are the best foods for muscle recovery after a workout?", "https://www.health.com/nutrition/muscle-recovery-foods");
        itemToLinkMap.put("What are some tips for staying hydrated during workouts?", "https://www.fitnessfirst.co.uk/blog/guide-to-staying-hydrated-before-during-and-after-a-workout");
        itemToLinkMap.put("How can I manage stress through exercise and relaxation techniques?", "https://www.health.harvard.edu/mind-and-mood/six-relaxation-techniques-to-reduce-stress");
        itemToLinkMap.put("What are the benefits of strength training for women?", "https://www.nebraskamed.com/primary-care/strength-training-for-women-has-many-benefits-and-its-never-too-late-to-start");
        itemToLinkMap.put("What are some effective ways to increase metabolism?", "https://www.piedmont.org/living-real-change/5-ways-to-boost-metabolism");
        itemToLinkMap.put("What are the dangers of overtraining?", "https://www.hss.edu/article_overtraining.asp");
        itemToLinkMap.put("How can I create a balanced workout routine?", "https://www.livestrong.com/article/528015-how-to-create-a-balanced-workout-routine/");
        itemToLinkMap.put("What are some healthy snacks for on-the-go energy?", "https://greatist.com/eat/portable-energy-boosting-snacks");
        itemToLinkMap.put("How can I prevent injuries during exercise?", "https://medlineplus.gov/ency/patientinstructions/000859.htm");
        itemToLinkMap.put("What are the health benefits of yoga and meditation?", "https://www.titanicspa.com/blog/the-benefits-of-yoga-and-meditation");
        itemToLinkMap.put("How can I improve my sleep quality for better recovery?", "https://www.jacorehab.com/blog/ways-to-improve-sleep-recovery/");
        itemToLinkMap.put("What are the recommended guidelines for physical activity for adults?", "https://www.cdc.gov/physicalactivity/basics/adults/index.htm");

        // Add other items and their corresponding links

        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        // Set click listener for each item in the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = adapter.getItem(position);
                if (selectedItem != null) {
                    String link = itemToLinkMap.get(selectedItem);
                    if (link != null) {

                        // Create a new instance of WebViewFragment
                        WebViewFragment webViewFragment = new WebViewFragment();

                        // Pass the URL to WebViewFragment
                        Bundle args = new Bundle();
                        args.putString("url", link);
                        webViewFragment.setArguments(args);

                        // Replace the current fragment with WebViewFragment
                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, webViewFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            }
        });

        return rootView;
    }
}
