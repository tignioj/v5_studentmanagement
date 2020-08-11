package com.tignioj.studentmanagement.gui.view.grademanage4;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;

public class ImageCenter {

    private static HashMap<String, Image> imageHashMap = new HashMap<>();


    public static Image getImage(String imagePath) {
        Image image = imageHashMap.get(imagePath);
        if (image == null) {
            image = pushImage(imagePath);
        }
        return image;
    }

    public static Image pushImage(String imagePath) {
        Image image = new Image(ImageCenter.class.getClassLoader().getResourceAsStream(imagePath));
        imageHashMap.put(imagePath, image);
        return image;
    }

    public static ImageView getImageView(String imgPath) {
        Image image = imageHashMap.get(imgPath);
        if (image == null) {
            image = pushImage(imgPath);
        }
        ImageView imageView = new ImageView(image);
        imageView.setId(imgPath);
        return imageView;
    }
}
