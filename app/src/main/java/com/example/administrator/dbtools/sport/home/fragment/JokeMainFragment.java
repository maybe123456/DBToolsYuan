package com.example.administrator.dbtools.sport.home.fragment;

import android.graphics.Color;
import android.view.View;

import com.example.administrator.dbtools.R;
import com.example.administrator.dbtools.base.BaseFragment;
import com.example.administrator.dbtools.databinding.JokeMainFragmentBinding;


public class JokeMainFragment extends BaseFragment<JokeMainFragmentBinding> {


    @Override
    public int getLayout() {
        return R.layout.joke_main_fragment;
    }

    @Override
    public void initView() {

//        binding.homeViewPager.setIndicatorHide();
        binding.homeViewPager.setOffscreenPageLimit(2);
        binding.homeViewPager.setUnderlineColor(Color.parseColor("#dddddd"));
        binding.homeViewPager.setLinkHide();
//        binding.homeViewPager.setUnderlineColor(R.color.mdddddd);
        binding.homeViewPager.setCurrentPage(0);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
