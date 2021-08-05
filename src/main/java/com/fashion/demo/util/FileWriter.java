package com.fashion.demo.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static com.fashion.demo.constant.ApplicationConstant.ITEM_ICON_IMG;
import static com.fashion.demo.constant.ApplicationConstant.USER_ICON_IMG;


/**
 * This class handle the operations of files
 */
@Component
public class FileWriter {

    private static final Logger LOGGER = LogManager.getLogger(FileWriter.class);

    private final Environment environment;
    private OutputStream outputStream;
    private BufferedOutputStream bufferedOutputStream;
    private static final String DEFAULT_EXTENSION = ".png";
    private static final String EXTENSION_FORMAT = ".%s";

    @Autowired
    public FileWriter(Environment environment) {
        this.environment = environment;
    }


    public String saveBase64File(String image, String type) {
        LOGGER.info("Start Function saveBase64File");
        try {
            String fileUploadDir = null;
            String fileDownloadDir = null;
            boolean isFullDetailsBase64 = true;

            // to find relevant upload and download direction
            switch (type) {
                case ITEM_ICON_IMG:
                    fileDownloadDir = environment.getRequiredProperty("fashion.image.event.icon.download.dir");
                    fileUploadDir = environment.getRequiredProperty("fashion.image.event.icon.upload.dir");
                    break;
//                case USER_BROCHURE_PDF:
//                    fileDownloadDir = environment.getRequiredProperty("tags.event.image.event.brochure.download.dir");
//                    fileUploadDir = environment.getRequiredProperty("tags.event.image.event.brochure.upload.dir");
//                    break;
                case USER_ICON_IMG:
                    fileDownloadDir = environment.getRequiredProperty("fashion.image.subevent.icon.download.dir");
                    fileUploadDir = environment.getRequiredProperty("fashion.image.subevent.icon.upload.dir");
                    break;
            }

            String[] imageParts = new String[2];
            if (image == null) return null;
            if (image.startsWith("data:")) {
                imageParts = image.split(",");
            } else {
                isFullDetailsBase64 = false;
                imageParts[1] = image;
            }

            byte[] data = DatatypeConverter.parseBase64Binary(imageParts[1]);
            String extension = isFullDetailsBase64 ? getExtension(image) : getImageType(data);
            String fileName = UUID.randomUUID().toString() + extension;

            Path path = Paths.get(fileUploadDir, fileName);
            File file = new File(path.toString());

            outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(data);
            outputStream.close();

            LOGGER.info("Function saveBase64File IMAGE PATH : " + fileDownloadDir + fileName);

            return fileDownloadDir + fileName;

        } catch (Exception e) {
            LOGGER.error("Function saveBase64File : " + e.getMessage(), e);
            return null;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    LOGGER.error("Function saveBase64File : " + e.getMessage(), e);
                }
            }
        }
    }

//    public String saveMultipartFile(MultipartFile file, String type) {
//
//        LOGGER.info("Start Function saveMultipartFile " + type);
//        try {
//            String fileUrl = null;
//            String extension = null;
//            String fileUploadDir = null;
//            String fileDownloadDir = null;
//            String name = UUID.randomUUID().toString();
//
//            switch (type){
//                case ATTACHMENT:
//                    extension = "pdf";
//                    fileUploadDir = environment.getRequiredProperty("tags.event.attachment.pdf.upload.dir");
//                    fileDownloadDir = environment.getRequiredProperty("tags.event.attachment.pdf.download.dir");
//                    break;
//                case EVENT_BROCHURE_PDF:
//                    extension = "pdf";
//                    fileDownloadDir = environment.getRequiredProperty("tags.event.image.event.brochure.download.dir");
//                    fileUploadDir = environment.getRequiredProperty("tags.event.image.event.brochure.upload.dir");
//                    break;
//                case SUB_EVENT_BROCHURE_PDF:
//                    extension = "pdf";
//                    fileDownloadDir = environment.getRequiredProperty("tags.event.image.subevent.brochure.download.dir");
//                    fileUploadDir = environment.getRequiredProperty("tags.event.image.subevent.brochure.upload.dir");
//                    break;
//            }
//
//            String originalFilename = file.getOriginalFilename();
//            if (originalFilename != null) {
//                String[] split = originalFilename.split("\\.");
//                extension = split[(split.length - 1)];
//            }
//
//
//            String fileName = name + FilenameUtils.EXTENSION_SEPARATOR_STR + extension;
//
//            Path path = Paths.get(fileUploadDir, fileName);
////            File serverFile = new File(path.toString());
//
//            file.transferTo(path);
//
//            fileUrl = fileDownloadDir + fileName;
//
//
//            LOGGER.info("Function saveMultipartFile ATTACHMENT PATH : " + fileUrl);
//
//            return fileUrl;
//
//        } catch (Exception e) {
//            LOGGER.error("Function saveMultipartFile : " + e.getMessage() + e);
//            e.printStackTrace();
//            throw new EventException(false, "Error occurred while saving attachment");
//        } finally {
//            if (bufferedOutputStream != null) {
//                try {
//                    bufferedOutputStream.close();
//                } catch (IOException e) {
//                    LOGGER.error("Function saveMultipartFile : " + e.getMessage() + e);
//                    throw new EventException(false, "Error occurred while saving attachment");
//                }
//            }
//        }
//
//    }


    private String getExtension(String base64ImageString) {
        try {
            String delims = "[,]";
            String[] parts = base64ImageString.split(delims);
            String imageString = parts[1];
            byte[] imageByteArray = Base64.decode(imageString);

            InputStream is = new ByteArrayInputStream(imageByteArray);

            //Find out image type
            String mimeType = null;
            String fileExtension = null;

            mimeType = URLConnection.guessContentTypeFromStream(is); //mimeType is something like "image/jpeg"
            String delimiter = "[/]";
            String[] tokens = mimeType.split(delimiter);
            fileExtension = tokens[1];
            return String.format(EXTENSION_FORMAT, fileExtension);
        } catch (Exception e) {
            LOGGER.error("Function getExtension : " + e.getMessage());
            return DEFAULT_EXTENSION;
        }
    }

    private boolean isMatch(byte[] pattern, byte[] data) {
        if (pattern.length <= data.length) {
            for (int idx = 0; idx < pattern.length; ++idx) {
                if (pattern[idx] != data[idx])
                    return false;
            }
            return true;
        }
        return false;
    }

    private String getImageType(byte[] data) {

//        filetype    magic number(hex)
//        jpg         FF D8 FF
//        gif         47 49 46 38
//        png         89 50 4E 47 0D 0A 1A 0A
//        bmp         42 4D
//        tiff(LE)    49 49 2A 00
//        tiff(BE)    4D 4D 00 2A

        final byte[] pngPattern = new byte[]{(byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A};
        final byte[] jpgPattern = new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF};
        final byte[] gifPattern = new byte[]{0x47, 0x49, 0x46, 0x38};
        final byte[] bmpPattern = new byte[]{0x42, 0x4D};
        final byte[] tiffLEPattern = new byte[]{0x49, 0x49, 0x2A, 0x00};
        final byte[] tiffBEPattern = new byte[]{0x4D, 0x4D, 0x00, 0x2A};

        if (isMatch(pngPattern, data))
            return "png";

        if (isMatch(jpgPattern, data))
            return "jpg";

        if (isMatch(gifPattern, data))
            return "gif";

        if (isMatch(bmpPattern, data))
            return "bmp";

        if (isMatch(tiffLEPattern, data))
            return "tif";

        if (isMatch(tiffBEPattern, data))
            return "tif";

        return "png";
    }


}
