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
 * show placeholders when the remote image is loading
 */

public class PlaceholderActivity extends AppCompatActivity {

    @BindView(R.id.btn_load_placeholder)
    Button mBtnLoadPlaceholder;

    @BindView(R.id.btn_load_error)
    Button mBtnLoadError;

    @BindView(R.id.btn_default_crossfade)
    Button mBtnDefaultAnimation;

    @BindView(R.id.btn_custom_annimation)
    Button mBtnCustomAnimation;

    @BindView(R.id.btn_dont_annimation)
    Button mBtnWithoutAnimation;

    @BindView(R.id.iv_show)
    ImageView mIvShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_placeholder);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_load_placeholder)
    public void clickLoadPlaceholder(){
        /**
         * load image from remote url,when the load begin,the placeholder will show first,after remote image loaded or the remote image has cache,
         * placeholder image remove,then show the remote image
         *
         * the placeholder image just a local drawable object or drawable resource id,not a remote image
         */
        Glide.with(PlaceholderActivity.this).load("http://i.imgur.com/Z3QjilA.jpg").placeholder(R.mipmap.placeholder).into(mIvShow);
    }

    @OnClick(R.id.btn_load_error)
    public void clickLoadError(){
        /**
         * load image from remote url,when the load begin,the placeholder will show first,after remote image loaded or the remote image has cache,
         * placeholder image remove,then show the remote image
         *
         * the placeholder image just a local drawable object or drawable resource id,not a remote image
         */
        Glide.with(PlaceholderActivity.this).load("http://www.google.com").placeholder(R.mipmap.placeholder).error(R.mipmap.error).into(mIvShow);
    }

    @OnClick(R.id.btn_default_crossfade)
    public void clickLoadDefaultAnimation(){
        /**
         * load image's annimation just valid in load() method,invalid in the placeholder or error image
         *
         * Glide ships with a standard crossfade animation, which is (for the version 3.6.1) active by placeholder
         *
         * placeholder crossFade annimation duration is 300ms,you can set duration by method .crossFade(2000)
         */
        Glide.with(PlaceholderActivity.this).load(R.mipmap.pizza).placeholder(R.mipmap.placeholder).crossFade(2000).into(mIvShow);
    }

    @OnClick(R.id.btn_custom_annimation)
    public void clickLoadCustomAnimation(){
        /**
         * load image's annimation
         */
        Glide.with(PlaceholderActivity.this).load(R.mipmap.sandwich).placeholder(R.mipmap.placeholder).animate(R.anim.left_anim).into(mIvShow);
    }

    @OnClick(R.id.btn_dont_annimation)
    public void clickLoadWithoutAnimation(){
        /**
         * forbidden loading image's annimation
         */
        Glide.with(PlaceholderActivity.this).load(R.mipmap.schweine).placeholder(R.mipmap.placeholder).dontAnimate().into(mIvShow);
    }
}
