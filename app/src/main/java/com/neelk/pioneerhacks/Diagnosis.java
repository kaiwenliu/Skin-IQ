package com.neelk.pioneerhacks;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Diagnosis extends AppCompatActivity {

    private TextView result;
    private String diseaseString;
    private static String[] disease = {"None", "Melanoma", "Dermatofibra", "Nevus"};
    private static int index = 0;
    private BottomNavigationView mBottomNavigationView;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
        setupNavigation();
        cardView = findViewById(R.id.final_cardView);
        cardView.setOnClickListener(cardViewOnClick);
        result = findViewById(R.id.diseaseTextView);
        result.setText(disease[index]);
        index++;
    }

    public void selectFragment(MenuItem item) {

        Intent intent;

        switch (item.getItemId()) {


            case R.id.menu_quiz:
                intent = new Intent(this, DiseaseInformation.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;


            case R.id.menu_settings:
                intent = new Intent(this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;

        }
    }

    private void setupNavigation() {
        mBottomNavigationView = findViewById(R.id.navigation_diagnose);
        mBottomNavigationView.setSelectedItemId(R.id.menu_home);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

    }

    private View.OnClickListener cardViewOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Uri link = Uri.parse("https://www.verywellhealth.com/common-skin-diseases-and-conditions-3996501");
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(link);
            startActivity(i);
        }
    };
}
