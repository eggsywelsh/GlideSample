package com.eggsy.glide.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eggsy on 17-1-12.
 * <p>
 * Shown to uses how to use in the ListView
 */

public class ListViewLoadActivity extends AppCompatActivity {

    @BindView(R.id.lv_content)
    ListView mLvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_listview);
        ButterKnife.bind(this);
        mLvContent.setAdapter(new ImageListAdapter(ListViewLoadActivity.this, eatFoodyImages));

    }

    public static String[] eatFoodyImages = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg",
    };

    private class ImageListAdapter extends ArrayAdapter {

        private Context context;
        private LayoutInflater inflater;

        private String[] imageUrls;

        public ImageListAdapter(Context context, String[] imageUrls) {
            super(context, R.layout.listview_item_image, imageUrls);

            this.context = context;
            this.imageUrls = imageUrls;

            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = inflater.inflate(R.layout.listview_item_image, parent, false);
            }

            /**
             * One awesomeness of Glide is that it automatically takes care of the request canceling,
             * clearing of the ImageViews, and loading the correct image into the appropriate ImageView
             */
            Glide
                    .with(context)
                    .load(imageUrls[position])
                    .into((ImageView) convertView);

            return convertView;
        }
    }
}
