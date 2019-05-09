package com.example.administrator.dbtools.base.network;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.dbtools.R;
import com.example.administrator.dbtools.base.App;
import com.example.administrator.dbtools.base.adapter.RecyclerViewAdapter;
import com.example.administrator.dbtools.base.util.CommonUtils;
import com.example.administrator.dbtools.base.util.NetStateUtils;
import com.example.administrator.dbtools.base.util.ToastUtil;
import com.example.administrator.dbtools.sport.home.bean.JokeBean;
import com.example.administrator.dbtools.sport.home.bean.SickMainBean;

import java.util.List;

import retrofit2.Call;

/**
 * Created by NaOH on 2018/8/3 15:11 (星期五).
 */
public abstract class ZPageSickLoadCallback<T> extends ZCallback<T> implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private RecyclerViewAdapter mAdapter;
    private int page = 1;
    private int pageSize = 20;
    private boolean isLoadMore;


    public ZPageSickLoadCallback(RecyclerViewAdapter adapter, RecyclerView recyclerView) {
        adapter.setOnLoadMoreListener(this, recyclerView);
        mAdapter = adapter;
    }

    public ZPageSickLoadCallback(RecyclerViewAdapter adapter, RecyclerView recyclerView, INetStatusUI netStatusUI) {
        this(adapter, recyclerView);
        setNetStatusUI(netStatusUI);
    }


    /**
     * 配置SwipeRefreshLayout,刷新自动请求数据
     *
     * @param swipeRefreshLayout
     */
    public void initSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout = swipeRefreshLayout;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 自定义每页请求的数量
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    protected void onSuccess(T data) {
        ZResponse zResponse = ((ZResponse) data);
        //非200即0
        if ((((SickMainBean)zResponse.getShowapi_res_body()).getRet_code()) == 0) {
            if (isLoadMore) {
                    mAdapter.addData((List) ((SickMainBean)zResponse.getShowapi_res_body()).getList());
            } else {
                mAdapter.setNewData((List) ((SickMainBean)zResponse.getShowapi_res_body()).getList());
            }
//            if (mAdapter.getData().size() == ((JokeBean)zResponse.getShowapi_res_body()).getAllNum()) {
                mAdapter.loadMoreEnd();
//            } else {
//                mAdapter.loadMoreComplete();
//            }

            page++;
        } else {
            if (isLoadMore) {
                mAdapter.loadMoreEnd();
            } else {
                mNetStatusUI.showEmpty();
                mAdapter.setNewData(null);
            }
        }
    }

    @Override
    protected void onCacheSuccess(T data) {
        ZResponse zResponse = ((ZResponse) data);
        //非200即0
        if ((((SickMainBean)zResponse.getShowapi_res_body()).getRet_code()) == 0) {
            if (isLoadMore) {
                    mAdapter.addData((List) ((SickMainBean)zResponse.getShowapi_res_body()).getList());
            } else {
                mAdapter.setNewData((List) ((SickMainBean)zResponse.getShowapi_res_body()).getList());
            }
//            if (page == zResponse.getTotalPage()) {
//            if (null == zResponse.getTotalCounts() ||mAdapter.getData().size() == zResponse.getTotalCounts()) {
//                if (null == zResponse.getTotalPage() ||page == zResponse.getTotalPage()) {
//            if (mAdapter.getData().size() == ((JokeBean)zResponse.getShowapi_res_body()).getAllNum()) {
                mAdapter.loadMoreEnd();
//            } else {
//                mAdapter.loadMoreComplete();
//            }
        } else {
            if (isLoadMore) {
                mAdapter.loadMoreEnd();
            } else {
                mAdapter.setNewData(null);
//                mNetStatusUI.showEmpty();
            }
        }

    }

    @Override
    public void onFailure(Call call, Throwable t) {
//        ToastUtil.toast(t.getMessage());
        onFinish(call);
        mAdapter.loadMoreFail();

        checkINetUIStatus();
    }

    private void checkINetUIStatus() {
        if (mNetStatusUI == null) return;
        if (mAdapter.getData().isEmpty()) {
            mNetStatusUI.showError();
        } else {
            mNetStatusUI.showContent();
        }
    }

    @Override
    public void handlePlaceHolder(int code) {

        if (mNetStatusUI != null) {
            if (code == 0) {
                mNetStatusUI.showContent();
            } else {
                if (mAdapter.getData().isEmpty()) {
                    mNetStatusUI.showEmpty();
                } else {
                    mNetStatusUI.showContent();
                }
            }
        }
    }

    @Override
    public void onRefresh() {
//        if (!NetStateUtils.isNetworkConnected(App.getApp())) {
//            ToastUtil.toast(App.getApp(), CommonUtils.getString(R.string.no_net));
//            mSwipeRefreshLayout.setRefreshing(false);
//            return;
//        }

        if (mSwipeRefreshLayout != null && !mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(true);

        isLoadMore = false;
        page = 1;
        requestAction(page, pageSize);
    }

    @Override
    public void onLoadMoreRequested() {
        if (!NetStateUtils.isNetworkConnected(App.getApp())) {
            ToastUtil.toast(App.getApp(), CommonUtils.getString(R.string.no_net));
            mAdapter.loadMoreFail();
            return;
        }
        isLoadMore = true;
        requestAction(page, pageSize);
    }

    public abstract void requestAction(int page, int pageSize);
}
