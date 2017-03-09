package com.iut.appmob.whataboutyou;

import android.view.View;

/**
 * Created by guydo on 09/03/2017.
 */

public interface Data {
    void finish();

    boolean isStarted();

    boolean isFinished();

    void bind(View v);
}