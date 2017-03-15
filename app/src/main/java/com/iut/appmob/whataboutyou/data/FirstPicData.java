package com.iut.appmob.whataboutyou.data;

import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.iut.appmob.whataboutyou.Data;
import com.iut.appmob.whataboutyou.R;
import com.iut.appmob.whataboutyou.User;

/**
 * Created by guydo on 09/03/2017.
 */

public class FirstPicData implements Data , View.OnClickListener{

    private boolean isFinished = false, isStarted = false;
    private AppCompatImageButton refresh;
    private LinearLayout layout;
    private ProgressBar progressBar;

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
                "/photos?fields=id",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        response.getJSONObject();
                        isFinished = true;
                    }
                }
        ).executeAsync();
    }

    @Override
    public void bind(View v) {
        progressBar = (ProgressBar) v.findViewById(R.id.progressBarFirstPic);
        layout = (LinearLayout) v.findViewById(R.id.firstPicLayout);
        refresh = (AppCompatImageButton) v.findViewById(R.id.refreshBtnFirstPic);
        refresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.refreshBtnFirstPic:
                if (!isFinished() && !isStarted()) {
                    refresh.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    finish();
                }
                break;
        }
    }
}