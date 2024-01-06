package com.example.fitnext;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DashBoard extends AppCompatActivity {
    FirebaseAuth auth;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        auth= FirebaseAuth.getInstance();


        //rest code of the onCreate() method

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
      navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId=item.getItemId();

                if(itemId==R.id.nav_home){
                    FragmentManager fragmentManager =getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container,new HomeFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                if(itemId==R.id.nav_about){
                    FragmentManager fragmentManager =getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container,new AboutFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                if(itemId==R.id.nav_logout){
                    logout();
                }
                if(itemId==R.id.nav_diet){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container,new DietPlanFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                if(itemId==R.id.nav_physical){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container,new PhysicalFitness());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                if(itemId==R.id.nav_share){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container,new ShareFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                if(itemId==R.id.nav_exit){
                    showExitConfirmationDialog();
                }
                if(itemId==R.id.nav_mental){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container,new MentalFitness());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }


                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "one", Toast.LENGTH_SHORT).show();
        updateSelectedNavItemBasedOnCurrentFragment();
        Toast.makeText(this, "two", Toast.LENGTH_SHORT).show();
        //if the drawer layout is open and the user click back so the drawer layout is closed
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        Toast.makeText(this, "three", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        Toast.makeText(this, "four", Toast.LENGTH_SHORT).show();

    }
    // Method to update selected item in the navigation drawer based on the current fragment
    private void updateSelectedNavItemBasedOnCurrentFragment() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (currentFragment instanceof HomeFragment) {
            navigationView.setCheckedItem(R.id.nav_home);
        } else if (currentFragment instanceof AboutFragment) {
            navigationView.setCheckedItem(R.id.nav_about);
        } else if (currentFragment instanceof DietPlanFragment) {
            navigationView.setCheckedItem(R.id.nav_diet);
        } else if (currentFragment instanceof PhysicalFitness) {
            navigationView.setCheckedItem(R.id.nav_physical);
        } else if (currentFragment instanceof ShareFragment) {
            navigationView.setCheckedItem(R.id.nav_share);
        } else if (currentFragment instanceof MentalFitness) {
            navigationView.setCheckedItem(R.id.nav_mental);
        }

        // Add more conditions based on your fragment classes
    }

    private void logout() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Do you really want to Logout?")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // If the user clicks "Yes", perform below operation
                        auth.signOut();
                        finishAffinity();
                        startActivity(new Intent(DashBoard.this,MainActivity.class));
                        Toast.makeText(DashBoard.this,"Logout Successful", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // If the user clicks "No", do nothing and close the dialog
                        dialog.dismiss();
                    }
                })
                .setIcon(R.drawable.alert)
                .show();

    }


    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Do you really want to exit the app?")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // If the user clicks yes
                        finishAffinity();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // If the user clicks no
                        dialog.dismiss();
                    }
                })
                .setIcon(R.drawable.alert)
                .show();
    }

}

