package com.neelk.pioneerhacks;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Settings extends AppCompatActivity implements OnMapReadyCallback {

    private BottomNavigationView mBottomNavigationView;
    private GoogleMap mMap;
    private static ArrayList<Coordinate> locations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setupNavigation();
        locations.add(new Coordinate(37.38742, -121.96408539999999, "Current Location"));
        locations.add(new Coordinate(37.253234, -121.949921, "Stanford Cancer Center South Bay"));
        locations.add(new Coordinate(37.38804, -121.971675, "Stanford General Dermatology"));
        locations.add(new Coordinate(37.367627, -122.081289, "The Cancer Center at El Camino Hospital"));

        if (this.mMap == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFrag.getMapAsync(this);

        }


    }

    public void selectFragment(MenuItem item) {

        Intent intent;

        switch (item.getItemId()) {

            case R.id.menu_home:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;


            case R.id.menu_quiz:
                intent = new Intent(this, DiseaseInformation.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;

        }
    }

    private void setupNavigation() {
        mBottomNavigationView = findViewById(R.id.navigation_settings);
        mBottomNavigationView.setSelectedItemId(R.id.menu_settings);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        for (int i = 1; i < 4; i++) {

            LatLng LOC = new LatLng(locations.get(i).getLat(), locations.get(i).getLng());
            googleMap.addMarker(new MarkerOptions().position(LOC).title(locations.get(i).getTitle()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(LOC));
        }

        CameraPosition camera_position = new CameraPosition.Builder().target(new LatLng(locations.get(0).getLat(), locations.get(0).getLng())).zoom(11).build();
        CameraUpdate updateCamera = CameraUpdateFactory.newCameraPosition(camera_position);
        googleMap.animateCamera(updateCamera);
    }
}
