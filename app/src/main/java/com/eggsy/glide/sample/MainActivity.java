package com.eggsy.glide.sample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.eggsy.permission.EPermission;
import com.permission.annotation.PermissionDeny;
import com.permission.annotation.PermissionGrant;

import java.io.File;

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

    @BindView(R.id.btn_notification)
    Button mBtnNotification;

    @BindView(R.id.btn_custom_model)
    Button mBtnCustomModel;

    @BindView(R.id.btn_dynamic_model)
    Button mBtnDynamicModel;

    @BindView(R.id.btn_rotate_image)
    Button mBtnRotateImage;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        ButterKnife.bind(this);

        EPermission.requestPermissions(MainActivity.this, REQUEST_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick(R.id.btn_start)
    public void clickBtnStart(View view) {
        Intent intent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.btn_advance_load)
    public void clickBtnLoading(View view) {
        Intent intent = new Intent(MainActivity.this, AdvanceLoadActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_listview)
    public void clickBtnListView(View view) {
        Intent intent = new Intent(MainActivity.this, ListViewLoadActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_gridview)
    public void clickBtnGridView(View view) {
        Intent intent = new Intent(MainActivity.this, GridViewLoadActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_placeholder)
    public void clickBtnPlaceholder(View view) {
        Intent intent = new Intent(MainActivity.this, PlaceholderActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_resizing)
    public void clickBtnResizing(View view) {
        Intent intent = new Intent(MainActivity.this, ImageResizingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_gifandvideo)
    public void clickBtnGifAndVideo(View view) {
        Intent intent = new Intent(MainActivity.this, GifAndVideoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_priority)
    public void clickBtnPriority(View view) {
        Intent intent = new Intent(MainActivity.this, PriorityActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_thumbnail)
    public void clickBtnThumbnail(View view) {
        Intent intent = new Intent(MainActivity.this, ThumbnailActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_target_view)
    public void clickTargetView(View view) {
        Intent intent = new Intent(MainActivity.this, TargetViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_notification)
    public void clickNotification(View view) {
        Intent intent = new Intent(MainActivity.this, NotificationsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_custom_model)
    public void clickCustomModel(View view) {
        Intent intent = new Intent(MainActivity.this, CustomRequestImageSizeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_dynamic_model)
    public void clickDynamicModel(View view) {
        Intent intent = new Intent(MainActivity.this, DynamicModelLoaderActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_rotate_image)
    public void clickRotateImage(View view) {
        Intent intent = new Intent(MainActivity.this, RotateImageActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EPermission.onRequestPermissionsResult(MainActivity.this, requestCode, permissions, grantResults);
    }

    @PermissionGrant(requestPermission = Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void grantSdcardPermission() {

        PackageManager pm = this.getPackageManager();
        String appName = getApplicationInfo().loadLabel(pm).toString();

        String externalCacheDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(externalCacheDir, appName + File.separator + Constant.file.path_image);
        if (!file.exists()) {
            boolean succ = file.mkdirs();
            Log.i(TAG, "make image dir " + succ);
        } else {
            Log.i(TAG, "image dir exist");
        }
    }

    @PermissionDeny(requestPermission = Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void denySdcardPermission() {
        File externalCacheDir = getApplicationContext().getExternalCacheDir();
        File file = new File(externalCacheDir, Constant.file.path_image);
        if (!file.exists()) {
            boolean succ = file.mkdirs();
            Log.i(TAG, "make image dir " + succ);
        } else {
            Log.i(TAG, "image dir exist");
        }
    }
}
