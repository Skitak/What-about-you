package com.iut.appmob.whataboutyou;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iut.appmob.whataboutyou.data.FirstPicData;
import com.iut.appmob.whataboutyou.data.FriendCount;
import com.iut.appmob.whataboutyou.data.StatData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Data> data;
    private InfoAdapter infoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        data = new ArrayList<Data>();

        data.add(new FirstPicData());

        data.add(new FriendCount());

        data.add(new StatData());

        infoAdapter = new InfoAdapter(data);

        recyclerView.setAdapter(infoAdapter);
    }
}