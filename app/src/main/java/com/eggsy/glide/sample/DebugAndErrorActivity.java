package com.eggsy.glide.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eggsy on 17-1-16.
 * <p>
 * setting debug level in the Glide and error listener
 * <p>
 * set debug level by adb shell,just like :
 * adb shell setprop log.tag.GenericRequest DEBUG
 * <p>
 * in the below example,we will receive error log:
 * 01-16 20:40:15.386 29548-29548/com.eggsy.glide.sample D/GenericRequest: load failed
 * java.net.SocketTimeoutException: connect timed out
 * at java.net.PlainSocketImpl.socketConnect(Native Method)
 * at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:334)
 * at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:196)
 * ...............
 */

public class DebugAndErrorActivity extends AppCompatActivity {

    private static final String TAG = DebugAndErrorActivity.class.getSimpleName();

    @BindView(R.id.btn_load)
    Button mBtnLoad;

    @BindView(R.id.iv_show)
    ImageView mIvShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_debug_error);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_load)
    public void clickLoad() {

        /**
         * set the request listener
         */
        Glide.with(DebugAndErrorActivity.this)
                .load("http://www.google.com")
                .listener(requestListener)
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.error).into(mIvShow);
    }

    private RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            // todo log exception
            Log.i(TAG, "request error");
            Toast.makeText(DebugAndErrorActivity.this, "request error,receive in the RequestListener", Toast.LENGTH_SHORT).show();
            // important to return false so the error placeholder can be placed
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            return false;
        }
    };


}
