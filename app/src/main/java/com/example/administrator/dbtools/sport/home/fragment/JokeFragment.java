package com.example.administrator.dbtools.sport.home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.dbtools.R;
import com.example.administrator.dbtools.base.BaseFragment;
import com.example.administrator.dbtools.base.network.RequestUtil;
import com.example.administrator.dbtools.base.network.ZClient;
import com.example.administrator.dbtools.base.network.ZPageLoadCallback;
import com.example.administrator.dbtools.base.network.ZResponse;
import com.example.administrator.dbtools.databinding.JokeSwipeRecyclerBinding;
import com.example.administrator.dbtools.sport.SportService;
import com.example.administrator.dbtools.sport.home.adapter.JokeListAdapter;
import com.example.administrator.dbtools.sport.home.bean.JokeBean;


public class JokeFragment extends BaseFragment<JokeSwipeRecyclerBinding> {

    private JokeListAdapter mAdapter;
    private ZPageLoadCallback callBack;
    private String status;

    @Override
    public int getLayout() {
        return R.layout.joke_swipe_recycler;
    }

    @Override
    public void initView() {
        showContent();
        binding.recyclerView.setAdapter(mAdapter = new JokeListAdapter(mContext));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        initCallback();
    }

    private void initCallback() {
        callBack = new ZPageLoadCallback<ZResponse<JokeBean>>(mAdapter, binding.recyclerView,this){
            @Override
            public void requestAction(int page, int limit) {
//                ZClient.getService(SportService.class).getMycollections(page,limit).enqueue(callBack);
                if("yt".equals(status)){
                    callBack.setCachKey("joke_cache"+page+status);
                    RequestUtil.cacheUpdate(ZClient.getService(SportService.class).getJokeList(page,limit),callBack);
                }else{
                    callBack.setCachKey("joke_cache"+page+status);
                    RequestUtil.cacheUpdate(ZClient.getService(SportService.class).getJokeTextList(page,limit),callBack);

                }

            }
        };

//        callBack.setDialog(loadingDialog);
//        callBack.setNetStatusUI(this);
        callBack.initSwipeRefreshLayout(binding.swipeRefreshLayout);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }


    @Override
    public void initListener() {
//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
////            @Override
////            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////                startActivity(new Intent(mContext, SimpleWebCommentActivity.class).putExtra("newsId", mAdapter.getItem(position).getNewsId()));
////            }
////        });
    }

    public JokeFragment setStatus(String status){
        this.status = status;
        return this;
    }

    @Override
    public void loadData() {
        mAdapter.setNewData(null);
        callBack.onRefresh();
    }

    @Override
    public void onClick(View v) {

    }
}
