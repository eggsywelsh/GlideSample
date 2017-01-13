package com.eggsy.glide.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eggsy on 17-1-13.
 * <p>
 * load gif and vedio by glide
 */

public class GifAndVideoActivity extends AppCompatActivity {

    @BindView(R.id.btn_gif)
    Button mBtnGif;

    @BindView(R.id.btn_show_gif_bitmap)
    Button mBtnGifBitmap;

    @BindView(R.id.btn_vedio)
    Button mBtnVedio;

    @BindView(R.id.iv_show)
    ImageView mIvShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_gif_video);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_gif)
    public void clickGif(View view) {
        /**
         * display remote gif,download gif first,so it maybe consuming some time
         */
        String gifUrl = "http://i.kinja-img.com/gawker-media/image/upload/s--B7tUiM5l--/gf2r69yorbdesguga10i.gif";
        Glide
                .with(GifAndVideoActivity.this)
                .load(gifUrl)
                // gif check,if load resource is not a gif,show error image,it not necessary to load and show gif
                .asGif()
                // not necessary
                .placeholder(R.mipmap.placeholder)
                // not necessary
                .error(R.mipmap.error)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mIvShow);
    }

    @OnClick(R.id.btn_show_gif_bitmap)
    public void clickGifBitmap(View view){
        String gifUrl = "http://i.kinja-img.com/gawker-media/image/upload/s--B7tUiM5l--/gf2r69yorbdesguga10i.gif";
        /**
         * In some cases, you might be interested in not displaying the entire Gif.
         * If you only want to display the first frame of the Gif,
         * you can call asBitmap() to guarantee the display as a regular image, even if the URL is pointing to a Gif.
         */
        Glide
                .with(GifAndVideoActivity.this)
                .load(gifUrl)
                .asBitmap()
                .into(mIvShow);
    }

    @OnClick(R.id.btn_vedio)
    public void clickVedio() {
        /**
         *
         */
        Glide.with(GifAndVideoActivity.this).load(R.mipmap.pizza).into(mIvShow);
    }
}
