package com.eggsy.glide.sample;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.NotificationTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eggsy on 17-1-14.
 * <p>
 * Setting the large notification image with the NotificationCompat.Builder is straight-forward when the notification image is in local.
 * However, if the image is not on the device and needs to be loaded from the Internet, it's impossible to use the standard tools.
 */

public class AppWidgetActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 99;

    private NotificationTarget notificationTarget;

    @BindView(R.id.btn_show_notification)
    Button mBtnShowNotification;

    @BindView(R.id.iv_show)
    ImageView mIvShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_notification);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_show_notification)
    public void clickShowNotification(View view) {
        final RemoteViews rv = new RemoteViews(getApplicationContext().getPackageName(), R.layout.custom_view_notification);

        rv.setImageViewResource(R.id.remoteview_notification_icon, R.mipmap.pizza);

        rv.setTextViewText(R.id.remoteview_notification_headline, "Headline");
        rv.setTextViewText(R.id.remoteview_notification_short_message, "Short Message");

        // build notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.pizza)
//                        .setContentTitle("Content Title")
//                        .setContentText("Content Text")
                        .setContent(rv)
                        .setPriority(NotificationCompat.PRIORITY_MIN);

        final Notification notification = mBuilder.build();

        // set big content view for newer androids
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            notification.bigContentView = rv;
        }

        /**
         * this will show notification first
         */
        NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, notification);

        /**
         * then set NotificationTarget,download the remote image for notification
         */
        notificationTarget = new NotificationTarget(
                getApplicationContext(),
                rv,
                // set the ImageView Id in the RemoteViews for remote image
                R.id.remoteview_notification_icon,
                notification,
                NOTIFICATION_ID);

        Glide
                .with(getApplicationContext()) // safer!
                .load("http://www.mit.edu/files/images/homepage/default/mit_logo.gif")
                .asBitmap()
                .into(notificationTarget);
    }


}
