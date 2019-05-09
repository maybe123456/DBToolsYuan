package com.example.administrator.dbtools.sport.home.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.dbtools.R;
import com.example.administrator.dbtools.base.adapter.RecyclerViewAdapter;
import com.example.administrator.dbtools.sport.home.bean.JokeBeanList;
import com.example.administrator.dbtools.sport.home.bean.SickSubListBean;

public class SickKeyAdapter extends RecyclerViewAdapter<JokeBeanList> {
    private Context context;

    public SickKeyAdapter(Context context) {
        super(R.layout.sick_fragment_item);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, JokeBeanList item) {
        helper.setText(R.id.title,item.getSummary());

    }
}
