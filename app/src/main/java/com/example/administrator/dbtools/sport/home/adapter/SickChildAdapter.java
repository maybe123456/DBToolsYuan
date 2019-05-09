package com.example.administrator.dbtools.sport.home.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.dbtools.R;
import com.example.administrator.dbtools.base.adapter.RecyclerViewAdapter;
import com.example.administrator.dbtools.sport.home.bean.SickMainListBean;
import com.example.administrator.dbtools.sport.home.bean.SickSubListBean;

public class SickChildAdapter extends RecyclerViewAdapter<SickSubListBean> {
    private Context context;

    public SickChildAdapter(Context context) {
        super(R.layout.sick_fragment_item);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SickSubListBean item) {
        helper.setText(R.id.title,item.getSubName());

    }
}
