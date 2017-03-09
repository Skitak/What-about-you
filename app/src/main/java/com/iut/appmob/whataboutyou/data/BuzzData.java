package com.iut.appmob.whataboutyou.data;

import android.view.View;

import com.iut.appmob.whataboutyou.Data;

/**
 * Created by guydo on 09/03/2017.
 */

public class BuzzData implements Data {
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

    }
}