package com.neelk.pioneerhacks;

import android.content.Context;
import android.graphics.Bitmap;

import org.datavec.image.loader.AndroidNativeImageLoader;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.io.ClassPathResource;
import org.nd4j.linalg.util.NDArrayUtil;

import java.io.IOException;

public class Classifier {


    public Classifier() {
    }

    public static int[] classify(Bitmap SkinImage, Context context) throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException {
       // String modelString = new ClassPathResource("skin_classifier.h5").getFile().getPath();
        // String modelString = "/res/skin_classifier.h5";
        // String modelString = context.getResources().get
       // String modelString = cont
       String modelString = "/Users/neel/Neel/Android%20Studio%20Projects/PioneerHacks/app/src/main/skin_classifier.h5";
      //  String modelString = "/app/src/main/skin_classifier.h5"
        MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(modelString);
        Bitmap imageToClassify = Bitmap.createScaledBitmap(SkinImage, 224, 224, false);
        AndroidNativeImageLoader loader = new AndroidNativeImageLoader();
        INDArray input = loader.asRowVector(imageToClassify);
        INDArray output = model.output(input);
        return NDArrayUtil.toInts(output);
    }

}
