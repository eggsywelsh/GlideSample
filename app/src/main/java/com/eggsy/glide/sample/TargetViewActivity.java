package com.eggsy.glide.sample;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.eggsy.glide.sample.view.CustomTargetView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eggsy on 17-1-13.
 * <p>
 * If you don't want to load images into an ImageView,Glide also offers an easy way to access the Bitmap of an image resource with Targets.
 * Targets are nothing else than callbacks, which return the result after Glide is done with all the loading and processing.
 */

public class TargetViewActivity extends AppCompatActivity {

    @BindView(R.id.btn_simple_target)
    Button mBtnSimpleTarget;

    @BindView(R.id.btn_simple_target_specific_size)
    Button mBtnSimpleTargetSpecifitSize;

    @BindView(R.id.btn_view_target)
    Button mBtnViewTarget;

    @BindView(R.id.iv_show)
    ImageView mIvShow;

    @BindView(R.id.custom_view)
    CustomTargetView customView;

    ViewTarget viewTarget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_target_view);
        ButterKnife.bind(this);

        viewTarget = new ViewTarget<CustomTargetView, GlideDrawable>(customView) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                this.view.setImage(resource.getCurrent());
            }
        };
    }

    @OnClick(R.id.btn_simple_target)
    public void clickSimpleTarget(View view) {
        Glide
                .with(TargetViewActivity.this) // could be an issue!
                .load("http://i.imgur.com/MoJs9pT.jpg")
                // must be use asBitmap before into(Target)
                .asBitmap()
                .into(target);
    }

    @OnClick(R.id.btn_simple_target_specific_size)
    public void clickSimpleTargetSpecificSize(View view) {
        Glide
                .with(TargetViewActivity.this) // could be an issue!
                .load("http://i.imgur.com/rFLNqWI.jpg")
                // must be use asBitmap before into(Target)
                .asBitmap()
                .into(targetSpecifitSize);
    }

    @OnClick(R.id.btn_view_target)
    public void clickViewTarget(View view) {
        Glide
                .with(getApplicationContext()) // safer!
                .load("http://i.imgur.com/rLR2cyc.jpg")
                .into(viewTarget);
    }

    /**
     * recommend to declare the target as field objects
     */
    SimpleTarget<Bitmap> target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
            mIvShow.setImageBitmap(bitmap);
        }
    };

    /**
     * recommend to declare the target as field objects
     *
     * set target bitmap size
     */
    SimpleTarget<Bitmap> targetSpecifitSize = new SimpleTarget<Bitmap>(300, 100) {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
            mIvShow.setImageBitmap(bitmap);
        }
    };
}
