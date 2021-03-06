package com.eggsy.glide.sample.loader;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import com.eggsy.glide.sample.model.CustomImageSizeModel;

/**
 * Created by eggsy on 17-1-16.
 */

public class CustomImageSizeUrlLoader extends BaseGlideUrlLoader<CustomImageSizeModel> {

    private static final String TAG = CustomImageSizeUrlLoader.class.getSimpleName();

    public CustomImageSizeUrlLoader(Context context) {
        super(context);
    }

    @Override
    protected String getUrl(CustomImageSizeModel model, int width, int height) {
        Log.i(TAG,"invoke CustomImageSizeUrlLoader.getUrl() method");
        return model.requestCustomSizeUrl( width, height );
    }
}
