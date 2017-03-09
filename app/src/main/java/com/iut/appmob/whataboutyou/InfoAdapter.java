package com.iut.appmob.whataboutyou;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iut.appmob.whataboutyou.data.BuzzData;
import com.iut.appmob.whataboutyou.data.FirstPicData;
import com.iut.appmob.whataboutyou.data.InfoPersoData;
import com.iut.appmob.whataboutyou.data.SharedData;
import com.iut.appmob.whataboutyou.data.StatData;

import java.util.List;

/**
 * Created by guydo on 02/03/2017.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {
    private static final int FIRST_PIC = 0, INFO_PERSO = 1, BUZZ = 2, SHARED = 3, STAT = 4;
    private List<Data> dataList;

    public InfoAdapter(List<Data> d) {
        dataList = d;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position) instanceof FirstPicData)
            return FIRST_PIC;
        else if (dataList.get(position) instanceof InfoPersoData)
            return INFO_PERSO;
        else if (dataList.get(position) instanceof BuzzData)
            return BUZZ;
        else if (dataList.get(position) instanceof SharedData)
            return SHARED;
        else if (dataList.get(position) instanceof StatData)
            return STAT;
        else
            return -1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FIRST_PIC)
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item_firstpic, parent, false));
        else if (viewType == INFO_PERSO)
            return null;
        else if (viewType == BUZZ)
            return null;
        else if (viewType == SHARED)
            return null;
        else if (viewType == STAT)
            return null;
        else
            return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Data d;

        public ViewHolder(View v) {
            super(v);
        }

        public void bind(Data data) {
            d = data;
            data.bind(super.itemView);
        }

        @Override
        public void onClick(View v) {
            if (!d.isFinished() && !d.isStarted())
                d.finish();
        }
    }
}