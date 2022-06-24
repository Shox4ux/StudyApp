package com.example.dictionary.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.dictionary.BookShop.StudySectionActivity;
import com.example.dictionary.Classes.ClassesActivity;
import com.example.dictionary.GrammarShop.GrammarSectionActivity;
import com.example.dictionary.R;
import com.example.dictionary.Registration.NetworkActivity;
import com.example.dictionary.Registration.ProfileActivity;
import com.example.dictionary.Registration.RegisterActivity;
import com.example.dictionary.Registration.SettingActivity;
import com.example.dictionary.TestShop.TestSectionActivity;
import com.example.dictionary.VocabularyShop.Complex.Comlex.ComplexVocabularyActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout; NavigationView navigationView; ImageView menuIcon; LinearLayout contentView;
    FirebaseUser currentUser;  FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.draw_layout);
        menuIcon = findViewById(R.id.menu_icon);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        navigationDrawer();
        updateNavHeader();
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });


    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }



    public void onClick(View view) {
        switch (view.getId()){

            case R.id.social_section:
                Intent netIntent = new Intent(MainActivity.this, ClassesActivity.class);
                startActivity(netIntent);
                break;

            case R.id.test_section:
                Intent testIntent = new Intent(MainActivity.this, TestSectionActivity.class);
                startActivity(testIntent);
                break;

            case R.id.vocab_section:
                Intent vocabIntent = new Intent(MainActivity.this, ComplexVocabularyActivity.class);
                startActivity(vocabIntent);
                break;

            case R.id.books_section:
                Intent bookIntent = new Intent(MainActivity.this, StudySectionActivity.class);
                startActivity(bookIntent);
                break;


            case R.id.grammar_section:
                Intent gramIntent = new Intent(MainActivity.this, GrammarSectionActivity.class);
                startActivity(gramIntent);
                break;


        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.nav_profile:
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                break;
            case R.id.nav_home:
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                break;
            case R.id.nav_settings:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
            case R.id.nav_about:
                startActivity(new Intent(MainActivity.this,NetworkActivity.class));
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                finish();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



    public void updateNavHeader(){
        TextView userNameHeader,userExperienceHeader;
        ImageView userPhotoHeader;

        navigationView = findViewById(R.id.navigation_view);
        View navHeader = navigationView.getHeaderView(0);
        userNameHeader = navHeader.findViewById(R.id.user_name);
        userExperienceHeader = navHeader.findViewById(R.id.user_experience);
        userPhotoHeader = navHeader.findViewById(R.id.user_photo);



        userNameHeader.setText(currentUser.getDisplayName());
        userExperienceHeader.setText(currentUser.getEmail());

        Glide.with(this).load(currentUser.getPhotoUrl()).into(userPhotoHeader);



    }
}