package com.eggsy.glide.sample.module;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;
import com.eggsy.glide.sample.Constant;
import com.eggsy.glide.sample.model.CustomImageSizeModel;
import com.eggsy.glide.sample.model.CustomImageSizeModelFactory;

import java.io.File;
import java.io.InputStream;

/**
 * Created by eggsy on 17-1-14.
 * <p>
 * custom Glide module
 */

public class CustomGlideModule implements GlideModule {

    private static final String TAG = CustomGlideModule.class.getSimpleName();

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        /**
         * default memory cache size
         * default bitmap pool size
         */
//        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
//        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
//        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        /*
         * module data
         */

        // get external disk image cache dir
        String externalImageDir = getLocalImageCacheDir(context);

        // max disk cache size 100MB
        int diskCacheSize = 100 * 1024 * 1024;
        // get the app's max memory of System relocate
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        // caculate the 1/8 max memory,used to the memory cache
        int memoryCacheSize = maxMemory / 8;
        /*
         *image decode formate
          */
        // high quality,uses 4 bytes for each pixel
        // builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        // Glide default setting,common quality,use two bytes for each pixel
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
        // set disk cache
        builder.setDiskCache(new DiskLruCacheFactory(externalImageDir, diskCacheSize));
        // or you can set disk cache by use below
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheSize));
//        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, diskCacheSize));


        // set memory cache to some specific location
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));
        // set bitmap pool
//        builder.setBitmapPool()

//        builder.setDiskCacheService()
    }

    private String getLocalImageCacheDir(Context context) {
        String externalImageDir = "";

        boolean isWriteSdcardPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        if (isWriteSdcardPermission) {
            PackageManager pm = context.getPackageManager();
            String appName = context.getApplicationInfo().loadLabel(pm).toString();

            String externalCacheDir = Environment.getExternalStorageDirectory().getAbsolutePath();

            externalImageDir = externalCacheDir + File.separator + appName + File.separator + Constant.file.path_image;

            File file = new File(externalImageDir);
            if (!file.exists()) {
                boolean succ = file.mkdirs();
                Log.i(TAG, "make image dir " + succ);
            } else {
                Log.i(TAG, "image dir exist");
            }
        } else {
            File externalCacheDir = context.getExternalCacheDir();

            externalImageDir = externalCacheDir + File.separator + Constant.file.path_image;

            File file = new File(externalImageDir);
            if (!file.exists()) {
                boolean succ = file.mkdirs();
                Log.i(TAG, "make image dir " + succ);
            } else {
                Log.i(TAG, "image dir exist");
            }
        }

        return externalImageDir;
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(CustomImageSizeModel.class, InputStream.class, new CustomImageSizeModelFactory());
    }


}
