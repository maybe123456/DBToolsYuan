package com.example.administrator.dbtools.sport.home.activity;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.dbtools.R;
import com.example.administrator.dbtools.base.BaseActivity;
import com.example.administrator.dbtools.base.BaseFragment;
import com.example.administrator.dbtools.base.network.RequestUtil;
import com.example.administrator.dbtools.base.network.ZClient;
import com.example.administrator.dbtools.base.network.ZPageSickLoadCallback;
import com.example.administrator.dbtools.base.network.ZResponse;
import com.example.administrator.dbtools.databinding.FragmentSickBinding;
import com.example.administrator.dbtools.sport.SportService;
import com.example.administrator.dbtools.sport.home.adapter.SickChildAdapter;
import com.example.administrator.dbtools.sport.home.adapter.SickGridAdapter;
import com.example.administrator.dbtools.sport.home.bean.SickMainBean;
import com.example.administrator.dbtools.sport.home.bean.SickSubListBean;

import java.io.Serializable;
import java.util.List;


public class SickAcitivity extends BaseActivity<FragmentSickBinding> {

    private SickChildAdapter mAdapter;
    private ZPageSickLoadCallback callBack;
    private List<SickSubListBean> subList;
    private String name;

    @Override
    public int getLayout() {
        return R.layout.fragment_sick;
    }

    @Override
    public void initView() {

        subList = (List<SickSubListBean>) getIntent().getSerializableExtra("subList");
        name = getIntent().getStringExtra("name");
        setTitle(name);
        showContent();
        binding.recyclerView.setAdapter(mAdapter = new SickChildAdapter(mContext));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

//        mAdapter = new SickGridAdapter(mContext);
//        binding.recyclerView.setAdapter(mAdapter);
//        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.titleBar.setVisibility(View.GONE);
        initCallback();
    }


    private void initCallback() {
//        callBack = new ZPageSickLoadCallback<ZResponse<SickMainBean>>(mAdapter, binding.recyclerView, this) {
//            @Override
//            public void requestAction(int page, int limit) {
////                ZClient.getService(SportService.class).getMycollections(page,limit).enqueue(callBack);
//                callBack.setCachKey("sick_cache" + page);
//                RequestUtil.cacheUpdate(ZClient.getService(SportService.class).getSickList(), callBack);
//            }
//
//
//        };
//
////        callBack.setDialog(loadingDialog);
////        callBack.setNetStatusUI(this);
//        callBack.initSwipeRefreshLayout(binding.swipeRefreshLayout);
        binding.swipeRefreshLayout.setEnabled(false);
        mAdapter.setNewData(subList);
    }


    @Override
    public void initListener() {

//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(mContext,SickKeyAcitivity.class);
//                intent.putExtra("name",mAdapter.getItem(position).getSubName());
//                intent.putExtra("subId",mAdapter.getItem(position).getSubId());
//                startActivity(intent);
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
