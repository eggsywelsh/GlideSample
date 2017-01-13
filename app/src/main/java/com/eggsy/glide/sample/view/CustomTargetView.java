package com.eggsy.glide.sample.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.eggsy.glide.sample.R;

/**
 * Created by eggsy on 17-1-13.
 */

public class CustomTargetView extends FrameLayout {

    ImageView iv;
    TextView tv;

    public void initialize(Context context) {
        inflate( context, R.layout.custom_view_target, this );

        iv = (ImageView) findViewById( R.id.custom_view_image );
        tv = (TextView) findViewById( R.id.custom_view_text );
    }

    public CustomTargetView(Context context, AttributeSet attrs) {
        super( context, attrs );
        initialize( context );
    }

    public CustomTargetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super( context, attrs, defStyleAttr );
        initialize( context );
    }

    public void setImage(Drawable drawable) {
//        iv = (ImageView) findViewById( R.id.custom_view_image );
        iv.setImageDrawable( drawable );
    }

}
