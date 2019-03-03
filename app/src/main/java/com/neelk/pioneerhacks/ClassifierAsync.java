package com.neelk.pioneerhacks;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class ClassifierAsync extends AsyncTask<Bitmap, Void, String> {

    private ProgressBar progressBar;
    private Context context;

    public ClassifierAsync(ProgressBar progressBar, Context context) {
        this.progressBar = progressBar;
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    protected String doInBackground(Bitmap... bitmaps) {

        Bitmap bitmap = bitmaps[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        int[] data = {0, 0, 0, 0, 0, 0, 0, 0};
        try {
            int[] newData = Classifier.classify(bitmap, context);
            Log.e("newData", Arrays.toString(newData));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKerasConfigurationException e) {
            e.printStackTrace();
        } catch (UnsupportedKerasConfigurationException e) {
            e.printStackTrace();
        }

        SparseArray<String> disease = new SparseArray<>();
        disease.put(0, "Actinic Keratosis");
        disease.put(1, "Atypical Melanocytic Proliferation");
        disease.put(2, "Basal Cell Carcinoma");
        disease.put(3, "Dermitafibrina");
        disease.put(4, "Lentigo");
        disease.put(5, "Melanoma");
        disease.put(6, "Nevus");
        disease.put(7, "Serborrheic Keratosis");

        int maxIndex = -1;
        int maxValue = -1;

        for (int i = 0; i < data.length; i++) {
            if (data[i] < maxValue) {
                maxValue = data[i];
                maxIndex = i;
            }
        }

        Log.e("values", Arrays.toString(data));

        return disease.get(maxIndex);

    }

    @Override
    protected void onPostExecute(String disease) {
        super.onPostExecute(disease);


        progressBar.setVisibility(ProgressBar.INVISIBLE);
        Toast.makeText(context, disease, Toast.LENGTH_LONG).show();


    }
}
