package com.eggsy.glide.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eggsy on 17-1-13.
 * <p>
 * show thumbnails in the activity
 * <p>
 * <p>
 * Thumbnails will be displayed until the actual request is loaded and processed.
 * If the thumbnail, for whatever reason, arrives after the original image,
 * it does not replace the original image. It simply will be dismissed.
 */

public class ThumbnailActivity extends AppCompatActivity {

    @BindView(R.id.btn_load_src)
    Button mBtnLoadSource;

    @BindView(R.id.btn_load_thumbnail)
    Button mBtnLoadThumbnail;


    @BindView(R.id.btn_load_advanced_thumbnail)
    Button mBtnLoadAdvanceThumbnail;

    @BindView(R.id.iv_show)
    ImageView mIvShow;

    @BindView(R.id.iv_show2)
    ImageView mIvShow2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_thumbnail);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_load_src)
    public void clickLoadSrc(View view) {
        Glide.with(ThumbnailActivity.this).load("http://i.imgur.com/rFLNqWI.jpg").into(mIvShow);
    }

    @OnClick(R.id.btn_load_thumbnail)
    public void clickLoadThumbnail(View view) {
        /**
         * in order to reload the remote image every click,this disable disk cache and memory cache
         *
         * in the below codes,it may be two situations:
         * 1.show the thumbnail image first,then thumbnail image has be removed,the source image apperaer
         * 2.show the source image directly
         */
        Glide.with(ThumbnailActivity.this)
                .load("http://i.imgur.com/MoJs9pT.jpg")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .thumbnail(0.2f)
                .into(mIvShow2);
    }

    /**
     * While the usage of .thumbnail() with a float parameter is easy to set up and can be very effective,
     * it doesn't always make sense. If the thumbnail needs to load the same full-resolution image over the network,
     * it might not be faster at all.so we use another way to load thumbnail.
     *
     * @param view
     */
    @OnClick(R.id.btn_load_advanced_thumbnail)
    public void clickLoadAdvanceThumbnail(View view) {

        /**
         * The difference is that the first thumbnail request is completely independent of the second original request.
         * The thumbnail can be a different resource or image URL, you can apply different transformations on it, and so on.
         */

        // setup Glide request without the into() method
        DrawableRequestBuilder<String> thumbnailRequest = Glide
                .with(ThumbnailActivity.this)
                .load("http://www.mit.edu/files/images/homepage/default/mit_logo.gif");

        // pass the request as a a parameter to the thumbnail request
        Glide.with(ThumbnailActivity.this)
                .load("http://i.imgur.com/MoJs9pT.jpg")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .thumbnail(thumbnailRequest)
                .into(mIvShow2);
    }
}
