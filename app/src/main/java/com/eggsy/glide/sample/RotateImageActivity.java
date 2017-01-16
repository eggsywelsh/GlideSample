package com.eggsy.glide.sample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eggsy on 17-1-16.
 *
 * rotate image when load the image
 */

public class RotateImageActivity extends AppCompatActivity {

    @BindView(R.id.btn_load)
    Button mBtnLoad;

    @BindView(R.id.btn_load_rotate)
    Button mBtnLoadRotate;

    @BindView(R.id.iv_show)
    ImageView mIvShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rotate_image);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_load)
    public void clickLoad() {
        Glide.with(RotateImageActivity.this).load(R.mipmap.pizza).into(mIvShow);
    }

    @OnClick(R.id.btn_load_rotate)
    public void clickLoadRotate() {
        Glide
                .with(RotateImageActivity.this)
                .load(R.mipmap.pizza)
                .transform(new RotateTransformation(RotateImageActivity.this, 90f))
                .into(mIvShow);
    }

    public class RotateTransformation extends BitmapTransformation {

        private float rotateRotationAngle = 0f;

        public RotateTransformation(Context context, float rotateRotationAngle) {
            super( context );

            this.rotateRotationAngle = rotateRotationAngle;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            Matrix matrix = new Matrix();

            matrix.postRotate(rotateRotationAngle);

            return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
        }

        @Override
        public String getId() {
            return "rotate" + rotateRotationAngle;
        }
    }
}
