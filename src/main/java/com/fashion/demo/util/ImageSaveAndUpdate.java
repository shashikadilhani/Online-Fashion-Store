package com.fashion.demo.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;



@Component
public class ImageSaveAndUpdate {

    private final FileWriter fileWriter;

    private static final Logger LOGGER = LogManager.getLogger(ImageSaveAndUpdate.class);

    public ImageSaveAndUpdate(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public String saveOrUpdateImage(String image, String type) {

        LOGGER.info("Start Function updateImage @ type : " + type);

        try {
            if (image == null) return null;
            else if (image.startsWith("data:")) return fileWriter.saveBase64File(image, type);
            else return image;
        } catch (Exception e) {
            return null;
        }
    }

}
