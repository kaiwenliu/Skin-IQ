package com.neelk.pioneerhacks;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

public class DiseaseInformation extends AppCompatActivity implements RecyclerViewAdapter.OnItemClicked {

    private RecyclerViewAdapter mAdapter;
    private ArrayList<Disease> disease;
    private RecyclerView recyclerView;
    private BottomNavigationView mBottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_information);

        initializeData();
        setupNavigation();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(DiseaseInformation.this);
        recyclerView.setLayoutManager(llm);

        recyclerView.setHasFixedSize(true);

        mAdapter = new RecyclerViewAdapter(DiseaseInformation.this, disease);

        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnClick(this);
    }

    private void initializeData() {
        disease = new ArrayList<>();

        disease.add(new Disease("Carcinoma", R.drawable.carcinoma, "https://www.webmd.com/cancer/what-is-carcinoma"));
        disease.add(new Disease("Dermatofibroma", R.drawable.dermatofibroma, "https://www.webmd.com/skin-problems-and-treatments/qa/what-are-dermatofibromas"));
        disease.add(new Disease("Lentigo", R.drawable.lentigo, "https://www.webmd.com/skin-problems-and-treatments/qa/what-are-lentigines"));
        disease.add(new Disease("Melanoma", R.drawable.melanoma, "https://www.webmd.com/melanoma-skin-cancer/default.htm"));
        disease.add(new Disease("Atypical Melanocyctic Proliferation", R.drawable.proliferation, "https://www.webmd.com/melanoma-skin-cancer/skin-mole-normal"));
        disease.add(new Disease("Nevus", R.drawable.nevus, "https://www.webmd.com/skin-problems-and-treatments/picture-of-dysplastic-nevi-atypical-moles-close-up"));
        disease.add(new Disease("Sebhorrheic Keratosis ", R.drawable.seborrheic_keratosis, "https://www.webmd.com/skin-problems-and-treatments/what-are-sebborheic-keratoses"));
        disease.add(new Disease("Actinic Keratosis ", R.drawable.actinic_keratosis, "https://www.webmd.com/skin-problems-and-treatments/understanding-actinic-keratosis-basics"));


    }

    @Override
    public void onItemClick(int position) {
        Uri link = Uri.parse(disease.get(position).getUrl());
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(link);
        startActivity(i);
    }

    public void selectFragment(MenuItem item) {

        Intent intent;

        switch (item.getItemId()) {

            case R.id.menu_home:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;


            case R.id.menu_settings:
                intent = new Intent(this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;

        }
    }

    private void setupNavigation() {
        mBottomNavigationView = findViewById(R.id.navigation_quiz);
        mBottomNavigationView.setSelectedItemId(R.id.menu_quiz);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

    }

}
