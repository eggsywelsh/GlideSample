package com.eggsy.glide.sample.model;

/**
 * Created by eggsy on 17-1-16.
 */

public class CustomImageSizeModelImpl implements CustomImageSizeModel {

    String baseImageUrl;

    public CustomImageSizeModelImpl(String baseImageUrl) {
        this.baseImageUrl = baseImageUrl;
    }

    @Override
    public String requestCustomSizeUrl(int width, int height) {
        // previous way: we directly accessed the images
        // http://www.mit.edu/files/images/homepage/default/mit_logo.gif

        // new way, server could handle additional parameter and provide the image in a specific size
        // in this case, the server would serve the image in 400x300 pixel size
        // http://www.mit.edu/files/images/homepage/default/mit_logo.gif?w=400&h=300
        return baseImageUrl + "?w=" + width + "&h=" + height;
    }
}
