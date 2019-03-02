package com.neelk.pioneerhacks;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CameraActivity extends AppCompatActivity {


    private ImageView loadedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        loadedImage = findViewById(R.id.analyzeImageView);


        Intent pictureIntent = getIntent();

        if (pictureIntent != null && pictureIntent.getStringExtra("value").equals("uri")) {
            loadedImage.setImageURI((Uri) pictureIntent.getParcelableExtra("uri"));


        } else if (pictureIntent != null && pictureIntent.getStringExtra("value").equals("bitmap")) {
            loadedImage.setImageBitmap((Bitmap) pictureIntent.getParcelableExtra("bitmap"));

        }


    }


}
