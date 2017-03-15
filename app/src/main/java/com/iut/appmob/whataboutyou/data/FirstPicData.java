package com.iut.appmob.whataboutyou.data;

import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.iut.appmob.whataboutyou.Data;
import com.iut.appmob.whataboutyou.R;
import com.iut.appmob.whataboutyou.User;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by guydo on 09/03/2017.
 */

public class FirstPicData implements Data , View.OnClickListener{

    private boolean isFinished = false, isStarted = false;
    private AppCompatImageButton refresh;
    private AppCompatImageView firstPic;
    private LinearLayout layout;

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

    @Override
    public void finish() {
        isStarted = true;
        new GraphRequest(
                User.getAccessToken(),
                User.getAccessToken().getUserId()+"/albums?fields=photos{images}",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        JSONObject jo = response.getJSONObject();
                        try {
                            layout.setVisibility(View.VISIBLE);
                            JSONObject joPic = jo.getJSONArray("data").getJSONObject(0).getJSONObject("photos").getJSONArray("data").getJSONObject(0).getJSONArray("images").getJSONObject(0);
                            Picasso.with(layout.getContext()).load(joPic.getString("source")).placeholder(R.drawable.loading_creation_room_black).into(firstPic);
                            isFinished = true;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            layout.setVisibility(View.GONE);
                            refresh.setVisibility(View.VISIBLE);
                            isStarted = false;
                        }

                    }
                }
        ).executeAsync();
    }

    @Override
    public void bind(View v) {
        layout = (LinearLayout) v.findViewById(R.id.firstPicLayout);
        firstPic = (AppCompatImageView) v.findViewById(R.id.firstPicImgView);
        refresh = 
        refresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.refreshBtnFirstPic:
                if (!isFinished() && !isStarted()) {
                    refresh.setVisibility(View.GONE);
                    finish();
                }
                break;
        }
    }
}