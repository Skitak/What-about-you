package com.iut.appmob.whataboutyou;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.iut.appmob.whataboutyou.data.FirstPicData;

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
        data = new ArrayList<Data>();

        data.add(new FirstPicData());

        infoAdapter = new InfoAdapter(data);

        recyclerView.setAdapter(infoAdapter);
    }
}