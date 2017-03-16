package com.iut.appmob.whataboutyou.data;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
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
 * Created by Bastien on 15/03/2017.
 */

public class FriendCount implements Data , View.OnClickListener {

    private ImageButton refresh;
    private ProgressBar progressBar;
    private TextView nbFriends;
    private TextView text;

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public void finish() {
        new GraphRequest(
            User.getAccessToken(),
                User.getAccessToken().getUserId()+"/friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        JSONObject jo = response.getJSONObject();
                        if (jo != null) {
                            try {
                                JSONObject ja = jo.getJSONObject("summary");
                                nbFriends.setText(ja.getString("total_count"));
                                progressBar.setVisibility(View.INVISIBLE);
                          } catch (JSONException e) {
                                e.printStackTrace();
                                progressBar.setVisibility(View.INVISIBLE);
                                nbFriends.setVisibility(View.INVISIBLE);
                                text.setVisibility(View.INVISIBLE);
                                refresh.setVisibility(View.VISIBLE);
                            }
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            nbFriends.setVisibility(View.INVISIBLE);
                            text.setVisibility(View.INVISIBLE);
                            refresh.setVisibility(View.VISIBLE);
                            Toast.makeText(text.getContext(), "Il n'y a pas internet", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        ).executeAsync();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void bind(View v) {
        refresh = (ImageButton) v.findViewById(R.id.imageButton);
        refresh.setOnClickListener(this);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        nbFriends = (TextView) v.findViewById(R.id.nbfriend);
        text = (TextView) v.findViewById(R.id.text);
    }

    @Override
    public void onClick(View v) {
        refresh.setVisibility(View.INVISIBLE);
        text.setVisibility(View.VISIBLE);
        nbFriends.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        finish();
    }
}
