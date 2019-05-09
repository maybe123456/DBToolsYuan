package com.example.administrator.dbtools.sport.home.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.administrator.dbtools.R;
import com.example.administrator.dbtools.base.BaseActivity;
import com.example.administrator.dbtools.base.network.RequestUtil;
import com.example.administrator.dbtools.base.network.ZClient;
import com.example.administrator.dbtools.base.network.ZPageSickLoadCallback;
import com.example.administrator.dbtools.base.network.ZResponse;
import com.example.administrator.dbtools.databinding.FragmentSickBinding;
import com.example.administrator.dbtools.sport.SportService;
import com.example.administrator.dbtools.sport.home.adapter.SickChildAdapter;
import com.example.administrator.dbtools.sport.home.adapter.SickKeyAdapter;
import com.example.administrator.dbtools.sport.home.bean.JokeBean;
import com.example.administrator.dbtools.sport.home.bean.SickMainBean;
import com.example.administrator.dbtools.sport.home.bean.SickSubListBean;

import java.util.List;


public class SickKeyAcitivity extends BaseActivity<FragmentSickBinding> {

    private SickKeyAdapter mAdapter;
    private ZPageSickLoadCallback callBack;
    private String name;
    private int subId;

    @Override
    public int getLayout() {
        return R.layout.fragment_sick;
    }

    @Override
    public void initView() {

        name = getIntent().getStringExtra("name");
        subId = getIntent().getIntExtra("subId",0);
        setTitle(name);
        showContent();
        binding.recyclerView.setAdapter(mAdapter = new SickKeyAdapter(mContext));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

//        mAdapter = new SickGridAdapter(mContext);
//        binding.recyclerView.setAdapter(mAdapter);
//        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.titleBar.setVisibility(View.GONE);
        initCallback();
    }


    private void initCallback() {
        callBack = new ZPageSickLoadCallback<ZResponse<JokeBean>>(mAdapter, binding.recyclerView, this) {
            @Override
            public void requestAction(int page, int limit) {
//                ZClient.getService(SportService.class).getMycollections(page,limit).enqueue(callBack);
//                callBack.setCachKey("sick_key_cache" + page);
                RequestUtil.cacheUpdate(ZClient.getService(SportService.class).getSickKeyList(subId,page), callBack);
            }


        };

//        callBack.setDialog(loadingDialog);
//        callBack.setNetStatusUI(this);
        callBack.initSwipeRefreshLayout(binding.swipeRefreshLayout);
    }


    @Override
    public void initListener() {

//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(mContext, SimpleWebCommentActivity.class).putExtra("newsId", mAdapter.getItem(position).getNewsId()));
//            }
//        });

    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void loadData() {
        mAdapter.setNewData(null);
//        callBack.onRefresh();
        initCallback();
//        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//            }
//        });
    }

    @Override
    public void onClick(View v) {

    }
}
