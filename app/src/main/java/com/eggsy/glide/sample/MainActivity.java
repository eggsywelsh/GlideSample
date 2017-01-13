package com.eggsy.glide.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_start)
    Button mBtnStart;

    @BindView(R.id.btn_advance_load)
    Button mBtnAdvanceLoad;

    @BindView(R.id.btn_listview)
    Button mBtnListView;

    @BindView(R.id.btn_gridview)
    Button mBtnGridView;

    @BindView(R.id.btn_placeholder)
    Button mBtnPlaceholder;

    @BindView(R.id.btn_resizing)
    Button mBtnResizing;

    @BindView(R.id.btn_priority)
    Button mBtnPriority;

    @BindView(R.id.btn_thumbnail)
    Button mBtnThumbnail;

    @BindView(R.id.btn_target_view)
    Button mBtnTargetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_start)
    public void clickBtnStart(View view){
        Intent intent = new Intent(MainActivity.this,StartActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.btn_advance_load)
    public void clickBtnLoading(View view){
        Intent intent = new Intent(MainActivity.this,AdvanceLoadActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_listview)
    public void clickBtnListView(View view){
        Intent intent = new Intent(MainActivity.this,ListViewLoadActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_gridview)
    public void clickBtnGridView(View view){
        Intent intent = new Intent(MainActivity.this,GridViewLoadActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_placeholder)
    public void clickBtnPlaceholder(View view){
        Intent intent = new Intent(MainActivity.this,PlaceholderActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_resizing)
    public void clickBtnResizing(View view){
        Intent intent = new Intent(MainActivity.this,ImageResizingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_gifandvideo)
    public void clickBtnGifAndVideo(View view){
        Intent intent = new Intent(MainActivity.this,GifAndVideoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_priority)
    public void clickBtnPriority(View view){
        Intent intent = new Intent(MainActivity.this,PriorityActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_thumbnail)
    public void clickBtnThumbnail(View view){
        Intent intent = new Intent(MainActivity.this,ThumbnailActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_target_view)
    public void clickTargetView(View view){
        Intent intent = new Intent(MainActivity.this,TargetViewActivity.class);
        startActivity(intent);
    }


}
