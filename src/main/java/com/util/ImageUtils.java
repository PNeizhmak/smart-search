package com.util;

import com.ImageProcessor;

import java.io.IOException;
import java.net.URL;

/**
 * @author Pavel Neizhmak
 */
public class ImageUtils {

    private static ImageProcessor imageProcessor = new ImageProcessor();

    public static String getDominantColorByPhoto(URL imageUrl) throws IOException {
        return imageProcessor.analyzeImage(imageUrl);
    }
 }
