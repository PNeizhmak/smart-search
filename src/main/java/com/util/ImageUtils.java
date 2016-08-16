package com.util;

import com.ImageProcessor;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

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

    public static String readColorFile(final String colorName) throws IOException {
        URL url = Resources.getResource(Constants.COLOR_RES_PATH + colorName + Constants.TXT_EXTENSION);

        return Resources.toString(url, Charsets.UTF_8);
    }
}
