package com.eggsy.glide.sample.model;

import android.content.Context;

import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.eggsy.glide.sample.loader.CustomImageSizeUrlLoader;

import java.io.InputStream;

/**
 * Created by eggsy on 17-1-16.
 */

public class CustomImageSizeModelFactory implements ModelLoaderFactory<CustomImageSizeModel,InputStream> {

    @Override
    public ModelLoader<CustomImageSizeModel, InputStream> build(Context context, GenericLoaderFactory factories) {
        return new CustomImageSizeUrlLoader(context);
    }

    @Override
    public void teardown() {

    }

}
