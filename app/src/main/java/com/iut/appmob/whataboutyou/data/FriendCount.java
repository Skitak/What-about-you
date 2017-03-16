package com.iut.appmob.whataboutyou.data;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.iut.appmob.whataboutyou.Data;
import com.iut.appmob.whataboutyou.R;

/**
 * Created by Bastien on 15/03/2017.
 */

public class FriendCount implements Data , View.OnClickListener {

    private ImageButton refresh;
    private ProgressBar progressBar;

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public void finish() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void bind(View v) {
        refresh = (ImageButton) v.findViewById(R.id.imageButton);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View v) {
        if (!isFinished() && !isStarted()) {

        }
    }
}
