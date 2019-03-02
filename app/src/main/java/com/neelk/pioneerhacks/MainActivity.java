package com.neelk.pioneerhacks;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private Button takePicture;
    private int CAMERA_REQUEST = 200;
    private int GALLERY_REQUEST = 201;
    private Uri imageUri;
    private int REQUEST_CANCELLED = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        takePicture = findViewById(R.id.takePictureButton);
        takePicture.setOnClickListener(takePictureOnClick);

    }

    private View.OnClickListener takePictureOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            loadCamera();
        }
    };

    private void loadCamera(){
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setPositiveButton("Take Picture", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File file = new File(Environment.getExternalStorageDirectory(), "skinImage.jpg");
                Uri uri = FileProvider.getUriForFile(MainActivity.this, "neel.provider", file);
                intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, CAMERA_REQUEST);


            }
        });
        builder.setNeutralButton("Choose from gallery", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST);


            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode != REQUEST_CANCELLED) {
            if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
                // Bundle extras = data.getExtras();
                File file = new File(Environment.getExternalStorageDirectory(), "profilePic.jpg");
                imageUri = FileProvider.getUriForFile(MainActivity.this, "neel.provider", file);
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                intent.putExtra("type", "uri");
                intent.putExtra("data", imageUri);
                startActivity(intent);
                // loadedImage.setImageURI(imageUri);


            } else if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
                imageUri = data.getData();
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);

                } catch (FileNotFoundException e) {
                    //  Tor(Settings.this, "Error opening profile picture!", Toast.LENGTH_SHORT, true).show();
                    Toast.makeText(MainActivity.this, "Error loading Image!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bitmap imageBitmap = BitmapFactory.decodeStream(inputStream);
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                intent.putExtra("type", "bitmap");
                intent.putExtra("data", imageBitmap);
                startActivity(intent);
                // profilePic.setImageBitmap(imageBitmap);
                //loadedImage.setImageBitmap(imageBitmap);
            }

        }
    }

}
