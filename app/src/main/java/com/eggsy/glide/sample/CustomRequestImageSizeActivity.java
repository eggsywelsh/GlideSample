package com.eggsy.glide.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.eggsy.glide.sample.model.CustomImageSizeModel;
import com.eggsy.glide.sample.model.CustomImageSizeModelImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eggsy on 17-1-16.
 */

public class CustomRequestImageSizeActivity extends AppCompatActivity {

    @BindView(R.id.btn_load)
    Button mBtnLoad;

    @BindView(R.id.iv_show)
    ImageView mIvShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_custom_image);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_load)
    public void clickLoad() {
        String baseImageUrl = "http://www.mit.edu/files/images/homepage/default/mit_logo.gif";
        CustomImageSizeModel customImageRequest = new CustomImageSizeModelImpl(baseImageUrl);
        /**
         * load remote image by custom model
         *
         * the real request url is http://www.mit.edu/files/images/homepage/default/mit_logo.gif?w=imageWidth&h=imageHeight
         */
        Glide.with(CustomRequestImageSizeActivity.this).load(customImageRequest).into(mIvShow);
    }
}
