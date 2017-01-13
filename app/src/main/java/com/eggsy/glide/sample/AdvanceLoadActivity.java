package com.eggsy.glide.sample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eggsy on 17-1-12.
 *
 * Advance load from File Object,local file path,network url and drawable resource id
 */

public class AdvanceLoadActivity extends AppCompatActivity {

    @BindView(R.id.btn_load_resource)
    Button mBtnLoadResource;

    @BindView(R.id.btn_load_uri)
    Button mBtnLoadUri;

    @BindView(R.id.btn_load_file)
    Button mBtnLoadFile;

    @BindView(R.id.btn_load_network)
    Button mBtnLoadNetwork;

    @BindView(R.id.btn_load_file_path)
    Button mBtnLoadFilePath;

    @BindView(R.id.iv_show)
    ImageView mIvShow;

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_advance_load);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_load_resource)
    public void clickLoadResource() {
        /**
         * load from resource
         */
        Glide.with(AdvanceLoadActivity.this).load(R.mipmap.pizza).into(mIvShow);
    }

    @OnClick(R.id.btn_load_uri)
    public void clickLoadUri() {
        /**
         * load from uri
         */
        Uri uri = resourceIdToUri(AdvanceLoadActivity.this, R.mipmap.sandwich);
        Glide.with(AdvanceLoadActivity.this).load(uri).into(mIvShow);
    }

    @OnClick(R.id.btn_load_file)
    public void clickLoadFile() {
        /**
         * load from file
         */
        try {
            /**
             * this just a example
             * copy asset's file to internal cache dir
             * then Glide load from FIle Object
             */
            InputStream is = getResources().getAssets().open("chips.jpg");
            File cacheDir = getCacheDir();
            FileOutputStream fos = new FileOutputStream(cacheDir.getAbsolutePath() + File.separator + "chips.jpg",
                    false);
            byte buffer[] = new byte[1024];
            int length = 0;

            while ((length = is.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            fos.close();
            is.close();

            File destFile = new File(cacheDir.getAbsolutePath() + File.separator + "chips.jpg");

            // load from File Object,the FIle Object may be from sdcard,internal dir or any path
            Glide.with(AdvanceLoadActivity.this).load(destFile).into(mIvShow);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.btn_load_network)
    public void clickLoadNetwork() {
        /**
         * load from network
         * should add user permission <uses-permission android:name="android.permission.INTERNET"></uses-permission> into Manifest file
         */
        Glide.with(AdvanceLoadActivity.this).load("http://www.mit.edu/files/images/homepage/default/mit_logo.gif").into(mIvShow);
    }

    @OnClick(R.id.btn_load_file_path)
    public void clickLoadFilePath() {
        /**
         * load from file path
         */
        try {
            /**
             * this just a example
             * copy asset's file to internal cache dir
             * then Glide load from File path
             */
            InputStream is = getResources().getAssets().open("schweine.jpg");
            File cacheDir = getCacheDir();
            FileOutputStream fos = new FileOutputStream(cacheDir.getAbsolutePath() + File.separator + "schweine.jpg",
                    false);
            byte buffer[] = new byte[1024];
            int length = 0;

            while ((length = is.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            fos.close();
            is.close();

            // load from File Object,the FIle Object may be from sdcard,internal dir or any path
            Glide.with(AdvanceLoadActivity.this).load(cacheDir.getAbsolutePath() + File.separator + "schweine.jpg").into(mIvShow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
}
