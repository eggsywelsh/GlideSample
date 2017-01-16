package com.eggsy.glide.sample.loader;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import com.eggsy.glide.sample.model.CustomImageSizeModel;

/**
 * Created by eggsy on 17-1-16.
 */

public class DynamicImageSizeUrlLoader extends BaseGlideUrlLoader<CustomImageSizeModel> {

    private static final String TAG = DynamicImageSizeUrlLoader.class.getSimpleName();

    public DynamicImageSizeUrlLoader(Context context) {
        super(context);
    }

    @Override
    protected String getUrl(CustomImageSizeModel model, int width, int height) {
        Log.i(TAG,"invoke DynamicImageSizeUrlLoader.getUrl() method");
        return model.requestCustomSizeUrl( width, height );
    }
}
