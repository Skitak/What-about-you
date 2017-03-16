package com.iut.appmob.whataboutyou.data;

import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.iut.appmob.whataboutyou.Data;
import com.iut.appmob.whataboutyou.R;
import com.iut.appmob.whataboutyou.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by guydo on 09/03/2017.
 */

public class StatData implements Data, View.OnClickListener {
    private boolean isFinished = false, isStarted = false;
    private AppCompatImageButton refresh;
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
                User.getAccessToken().getUserId() + "/albums?fields=photo_count",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        JSONObject jo = response.getJSONObject();
                        if (jo != null) {
                            try {
                                TextView countPic = ((TextView) layout.getChildAt(0));
                                countPic.setText(countPic.getText() + jo.getJSONArray("data").getJSONObject(0).getString("photo_count"));
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
        layout = (LinearLayout) v.findViewById(R.id.layoutStatPic);
        refresh = (AppCompatImageButton) v.findViewById(R.id.refreshBtnStatPic);
        refresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.refreshBtnStatPic:
                if (!isFinished() && !isStarted()) {
                    refresh.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                    finish();
                }
                break;
        }
    }
}