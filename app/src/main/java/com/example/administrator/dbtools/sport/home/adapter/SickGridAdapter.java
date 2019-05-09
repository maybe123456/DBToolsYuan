package com.example.administrator.dbtools.sport.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.dbtools.R;
import com.example.administrator.dbtools.base.adapter.RecyclerViewAdapter;
import com.example.administrator.dbtools.base.network.GlideApp;
import com.example.administrator.dbtools.sport.home.bean.Football;
import com.example.administrator.dbtools.sport.home.bean.SickMainBean;
import com.example.administrator.dbtools.sport.home.bean.SickMainListBean;

public class SickGridAdapter extends RecyclerViewAdapter<SickMainListBean> {
    private Context context;

    public SickGridAdapter(Context context) {
        super(R.layout.sick_fragment_item);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SickMainListBean item) {
        helper.setText(R.id.title,item.getTypeName());

    }
}
