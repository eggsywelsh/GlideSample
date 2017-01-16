package com.eggsy.glide.sample;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
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
 * <p>
 * rotate image when load the image
 */

public class TransformationActivity extends AppCompatActivity {

    @BindView(R.id.btn_load)
    Button mBtnLoad;

    @BindView(R.id.btn_load_rotate)
    Button mBtnLoadRotate;

    @BindView(R.id.btn_load_blur)
    Button mBtnLoadBlur;

    @BindView(R.id.btn_load_multi)
    Button mBtnLoadMulti;

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
        Glide.with(TransformationActivity.this).load(R.mipmap.pizza).into(mIvShow);
    }

    @OnClick(R.id.btn_load_rotate)
    public void clickLoadRotate() {
        Glide
                .with(TransformationActivity.this)
                .load(R.mipmap.pizza)
                .transform(new RotateTransformation(TransformationActivity.this, 45f))
                // this would work too!
//                .bitmapTransform(new RotateTransformation(TransformationActivity.this, 45f))
                .into(mIvShow);
    }

    @OnClick(R.id.btn_load_blur)
    public void clickLoadBlur() {
        Glide
                .with(TransformationActivity.this)
                .load(R.mipmap.pizza)
                .transform(new BlurTransformation(TransformationActivity.this))
                // this would work too!
//                .bitmapTransform(new BlurTransformation(TransformationActivity.this))
                .into(mIvShow);
    }

    @OnClick(R.id.btn_load_multi)
    public void clickLoadMulti() {
        Glide
                .with(TransformationActivity.this)
                .load(R.mipmap.pizza)
                .transform(new RotateTransformation(TransformationActivity.this, 45f),new BlurTransformation(TransformationActivity.this))
                // this would work too!
//                .bitmapTransform(new RotateTransformation(TransformationActivity.this, 45f),new BlurTransformation(TransformationActivity.this))
                .into(mIvShow);
    }

    public class RotateTransformation extends BitmapTransformation {

        private float rotateRotationAngle = 0f;

        public RotateTransformation(Context context, float rotateRotationAngle) {
            super(context);

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
            /**
             * The getId() method describes an unique identifier for this particular transformation.
             * Glide uses that key as part of the caching system.
             * Makes sure you make it unique to avoid unexpected issues
             */
            return "rotate" + rotateRotationAngle;
        }
    }

    public class BlurTransformation extends BitmapTransformation {

        private RenderScript rs;

        public BlurTransformation(Context context) {
            super(context);

            rs = RenderScript.create(context);
        }

        @Override
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            Bitmap blurredBitmap = toTransform.copy(Bitmap.Config.ARGB_8888, true);

            // Allocate memory for Renderscript to work with
            Allocation input = Allocation.createFromBitmap(
                    rs,
                    blurredBitmap,
                    Allocation.MipmapControl.MIPMAP_FULL,
                    Allocation.USAGE_SHARED
            );
            Allocation output = Allocation.createTyped(rs, input.getType());

            // Load up an instance of the specific script that we want to use.
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setInput(input);

            // Set the blur radius
            script.setRadius(10);

            // Start the ScriptIntrinisicBlur
            script.forEach(output);

            // Copy the output to the blurred bitmap
            output.copyTo(blurredBitmap);

            toTransform.recycle();

            return blurredBitmap;
        }

        @Override
        public String getId() {
            return "blur";
        }
    }
}
