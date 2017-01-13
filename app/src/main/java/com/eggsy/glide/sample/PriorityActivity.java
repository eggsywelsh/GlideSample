package com.eggsy.glide.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eggsy on 17-1-13.
 *
 * load the multi images by set priority property
 */

public class PriorityActivity extends AppCompatActivity {

    @BindView(R.id.btn_load_priority)
    Button mBtnMultiImages;

    @BindView(R.id.iv_show)
    ImageView mIvShow;

    @BindView(R.id.iv_show2)
    ImageView mIvShow2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_priority);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_load_priority)
    public void clickLoadMultiImages(View view){
        /**
         * Priority.LOW
         * Priority.NORMAL
         * Priority.HIGH
         * Priority.IMMEDIATE
         *
         * the priorities are not completely strict.
         * Glide will use them as a guideline and process the requests with the best effort possible,
         * but it's not guaranteed that all images will be loaded in the requested order.
         *
         */
        Glide.with(PriorityActivity.this).load("http://i.imgur.com/rFLNqWI.jpg").priority(Priority.LOW).animate(R.anim.left_anim).into(mIvShow);
        Glide.with(PriorityActivity.this).load("http://i.imgur.com/Z3QjilA.jpg").priority(Priority.HIGH).animate(R.anim.left_anim).into(mIvShow2);
    }
}
