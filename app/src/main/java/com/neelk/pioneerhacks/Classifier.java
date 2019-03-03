package com.neelk.pioneerhacks;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.Bitmap;

import org.apache.commons.compress.utils.IOUtils;
import org.datavec.image.loader.AndroidNativeImageLoader;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.io.ClassPathResource;
import org.nd4j.linalg.util.NDArrayUtil;
import org.tensorflow.lite.Interpreter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.MappedByteBuffer;
import java.nio.charset.StandardCharsets;

public class Classifier {


    public Classifier() {
    }

    public static int[] classify(Bitmap SkinImage, Context context) throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException {
        // String modelString = new ClassPathResource("skin_classifier.h5").getFile().getPath();
        // String modelString = "/res/skin_classifier.h5";
        // String modelString = context.getResources().get
        // String modelString = cont
        // String modelString = "/Users/neel/Neel/Android%20Studio%20Projects/PioneerHacks/app/src/main/skin_classifier.h5";
        //  String modelString = "/app/src/main/skin_classifier.h5"

//        InputStream inputStream = context.getResources().openRawResource(R.raw.skin_classifier);
//        MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(inputStream);
//        Bitmap imageToClassify = Bitmap.createScaledBitmap(SkinImage, 224, 224, false);
//        AndroidNativeImageLoader loader = new AndroidNativeImageLoader();
//        INDArray input = loader.asMatrix(imageToClassify);
//        INDArray output = model.output(input);
//        return NDArrayUtil.toInts(output);

        //Interpreter tfLite = new Interpreter(loadModelFile());
        return null;
    }

    private static String inputStreamtoString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private static MappedByteBuffer loadModelFile(Context context) throws IOException {
        AssetFileDescriptor fileDescriptor = context.getAssets().openFd("linear.tflite");
        return null;
    }
}
