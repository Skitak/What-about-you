package com.iut.appmob.whataboutyou.data;

import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.iut.appmob.whataboutyou.Data;
import com.iut.appmob.whataboutyou.R;
import com.iut.appmob.whataboutyou.User;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by guydo on 09/03/2017.
 */

public class FirstPicData implements Data , View.OnClickListener{

    private boolean isFinished = false, isStarted = false;
    private AppCompatImageButton refresh;
    private AppCompatImageView firstPic, lastPic;
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
                        if (jo != null) {
                            try {
                                JSONArray ja = jo.getJSONArray("data").getJSONObject(0).getJSONObject("photos").getJSONArray("data");
                                JSONObject joFirstPic = ja.getJSONObject(0).getJSONArray("images").getJSONObject(0), joLastPic = ja.getJSONObject(ja.length() - 1).getJSONArray("images").getJSONObject(0);
                                Picasso.with(layout.getContext()).load(joFirstPic.getString("source")).fit().placeholder(R.drawable.loading_creation_room_black).into(firstPic);
                                Picasso.with(layout.getContext()).load(joLastPic.getString("source")).fit().placeholder(R.drawable.loading_creation_room_black).into(lastPic);
                                //layout.getChildAt(0).setVisibility(View.VISIBLE);
                                isFinished = true;
                            } catch (JSONException e) {
                                e.printStackTrace();
                                layout.setVisibility(View.GONE);
                                refresh.setVisibility(View.VISIBLE);
                                isStarted = false;
                            }
                        } else {
                            layout.setVisibility(View.GONE);
                            refresh.setVisibility(View.VISIBLE);
                            isStarted = false;
                            Toast.makeText(layout.getContext(), "Il n'y a pas internet", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        ).executeAsync();
    }

    @Override
    public void bind(View v) {
        layout = (LinearLayout) v.findViewById(R.id.firstPicLayout);
        firstPic = (AppCompatImageView) v.findViewById(R.id.firstPicImgView);
        lastPic = (AppCompatImageView) v.findViewById(R.id.lastPicImgView);
        refresh = (AppCompatImageButton) v.findViewById(R.id.refreshBtnFirstPic);
        refresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.refreshBtnFirstPic:
                if (!isFinished() && !isStarted()) {
                    refresh.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                    finish();
                }
                break;
        }
    }
}