package com.eggsy.glide.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eggsy on 17-1-13.
 *
 * resizing image's width and height before load into ImageView
 */

public class ImageResizingActivity extends AppCompatActivity {

    @BindView(R.id.btn_resizing)
    Button mBtnResizing;

    @BindView(R.id.btn_scaling_center_crop)
    Button mBtnScalingCenterCrop;

    @BindView(R.id.btn_scaling_fit_center)
    Button mBtnScalingFitCenter;

    @BindView(R.id.iv_show)
    ImageView mIvShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_image_resizing);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_resizing)
    public void clickResizing(){
        /**
         * Glide automatically limits the size of the image it holds in cache and memory to the ImageView dimensions
         * With Glide, if the image should not be automatically fitted to the ImageView,
         * call override(horizontalSize, verticalSize). This will resize the image before displaying it in the ImageView.
         *
         * override method's parameters unit is pixel
         */
        Glide.with(ImageResizingActivity.this).load(R.mipmap.pizza).override(600,200).into(mIvShow);
    }

    @OnClick(R.id.btn_scaling_center_crop)
    public void clickScalingCenterCrop(){
        /**
         * scales the image so that it fills the requested bounds of the ImageView and then crops the extra.
         * The ImageView will be filled completely, but the entire image might not be displayed
         */
        Glide.with(ImageResizingActivity.this).load(R.mipmap.pizza).override(900,300).centerCrop().into(mIvShow);
    }

    @OnClick(R.id.btn_scaling_fit_center)
    public void clickScalingFitCenter(){
        /**
         * scales the image so that both dimensions are equal to or less than the requested bounds of the ImageView.
         * The image will be displayed completely, but might not fill the entire ImageView
         */
        Glide.with(ImageResizingActivity.this).load(R.mipmap.pizza).override(900,300).fitCenter().into(mIvShow);
    }
}
