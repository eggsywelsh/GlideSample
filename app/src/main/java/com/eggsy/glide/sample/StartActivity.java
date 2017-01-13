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
 * Created by eggsy on 17-1-12.
 *
 * Simplest example to show how to load image
 */

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.btn_load)
    Button mBtnLoad;

    @BindView(R.id.iv_show)
    ImageView mIvShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_start);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_load)
    public void clickLoad(){
        /**
         * base use of Glide.
         * Glide builder requires at least three parameters for a fully functional request, the methods are
         * with(Context context)  load(String imageUrl)  into(ImageView targetImageView)
         */
        Glide.with(StartActivity.this).load(R.mipmap.pizza).into(mIvShow);
    }
}
