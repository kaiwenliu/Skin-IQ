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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CameraActivity extends AppCompatActivity {


    private ImageView loadedImage;
    private Button analyzeImageButton;
    private Bitmap imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        analyzeImageButton = findViewById(R.id.analyzeImageButton);
        loadedImage = findViewById(R.id.analyzeImageView);


        Intent pictureIntent = getIntent();

        if (pictureIntent != null) {
            //loadedImage.setImageURI((Uri) pictureIntent.getParcelableExtra("data"));
            imageUri = (Bitmap) pictureIntent.getParcelableExtra("data");
            Picasso.get().load(imageUri).into(loadedImage);
            Log.e("after intent", pictureIntent.getParcelableExtra("data").toString());


        }


    }

    private View.OnClickListener analyzeOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };


}
