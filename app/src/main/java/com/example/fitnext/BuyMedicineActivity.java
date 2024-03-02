package com.example.fitnext;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BuyMedicineActivity extends AppCompatActivity {

    private List<Medicine> medicines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        populateMedicines();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MedicineAdapter adapter = new MedicineAdapter(medicines);
        recyclerView.setAdapter(adapter);
    }

    private void populateMedicines() {
        medicines.add(new Medicine("Netmeds", "https://entrackr.com/storage/2020/08/netmeds-1-800x400.jpg", "https://www.netmeds.com"));
        medicines.add(new Medicine("TATA 1mg", "https://static.startuptalky.com/2021/03/1mg-startup-story_startuptalky.jpg", "https://www.1mg.com"));
        medicines.add(new Medicine("PharmEasy", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6ysbjVGtgO8QnXdLrXSJxuMpNFcXp-Di2EQ&usqp=CAU", "https://pharmeasy.in"));
        medicines.add(new Medicine("Practo", "https://blog.practo.com/wp-content/uploads/2020/05/1.png", "https://www.practo.com/"));
        medicines.add(new Medicine("MedPlus", "https://mir-s3-cdn-cf.behance.net/projects/404/82cbdd139426673.Y3JvcCw5OTksNzgyLDAsMTA4.jpeg", "https://www.medplusmart.com/"));
        medicines.add(new Medicine("Healthkart", "https://entrackr.com/storage/2022/06/Healthkart.jpg", "https://www.healthkart.com/"));
        medicines.add(new Medicine("MediBuddy", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2l51N8nvMf7sQLq2wA9uUIgjKRlb4FLpa6A&usqp=CAU", "https://www.medibuddy.in/"));
        medicines.add(new Medicine("Apollo Pharmacy", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSm8m0tdNCuEeg0fZsKeX7z3sB2hwG1FrLO2g&usqp=CAU", "https://www.apollopharmacy.in/"));
    }

    private static class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder> {

        private List<Medicine> medicines;

        public MedicineAdapter(List<Medicine> medicines) {
            this.medicines = medicines;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicine_card, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Medicine medicine = medicines.get(position);
            holder.medicineNameTextView.setText(medicine.getName());
            Glide.with(holder.itemView.getContext()).load(medicine.getAvatarImageUrl()).into(holder.medicineImageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = medicine.getWebsiteLink();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    v.getContext().startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return medicines.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView medicineImageView;
            TextView medicineNameTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                medicineImageView = itemView.findViewById(R.id.medicineImageView);
                medicineNameTextView = itemView.findViewById(R.id.medicineNameTextView);
            }
        }
    }
}
