package com.neelk.pioneerhacks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.io.ClassPathResource;

import java.io.IOException;

public class CameraActivity extends AppCompatActivity {


    private ImageView loadedImage;
    private Button analyzeImageButton;
    private Uri imageUri;
    private ProgressBar progressBar;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        analyzeImageButton = findViewById(R.id.analyzeImageButton);
        loadedImage = findViewById(R.id.analyzeImageView);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setIndeterminate(true);

        Intent pictureIntent = getIntent();

        if (pictureIntent != null) {
            //loadedImage.setImageURI((Uri) pictureIntent.getParcelableExtra("data"));
            imageUri = (Uri) pictureIntent.getParcelableExtra("data");
            Picasso.get().load(imageUri).fit().into(loadedImage, new Callback() {
                @Override
                public void onSuccess() {
                    loadedImage.invalidate();
                    BitmapDrawable drawable = (BitmapDrawable) loadedImage.getDrawable();
                    bitmap = drawable.getBitmap();
                }

                @Override
                public void onError(Exception e) {

                }
            });

            analyzeImageButton.setOnClickListener(analyzeOnClick);
            // Bitmap bitmap = loadedImage.getDrawable().getBit


        }


    }

    private View.OnClickListener analyzeOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            new ClassifierAsync(progressBar, CameraActivity.this).execute(bitmap);

        }
    };


}
