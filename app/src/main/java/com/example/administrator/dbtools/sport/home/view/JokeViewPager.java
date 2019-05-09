package com.example.administrator.dbtools.sport.home.view;

import android.content.Context;
import android.util.AttributeSet;

import com.example.administrator.dbtools.base.BaseFragment;
import com.example.administrator.dbtools.base.customtab.CustomViewPager;
import com.example.administrator.dbtools.sport.home.fragment.JokeFragment;


public class JokeViewPager extends CustomViewPager {

    public JokeViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public JokeViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JokeViewPager(Context context) {
        this(context, null, 0);
    }

    @Override
    public BaseFragment[] initViewPagerFragments() {

//        String obligation="我处理的";
//        String obtaingoods="全部信息";

        String[] titles = {"有图", "文本"};
        BaseFragment[] tabFragments = new BaseFragment[titles.length];
        for (int i = 0, size = tabFragments.length; i < size; i++) {
//                tabFragments[i] = new HomeFeedFragment();
            String create = null;
            switch (i) {
                case 0:
                    create = "yt";
                    break;
                case 1:
                    create = "wb";
                    break;
            }
            tabFragments[i] = new JokeFragment().setStatus(create);
//            }
            //设置tab标题
            tabFragments[i].setMyTitle(titles[i]);
        }
        return tabFragments;
    }
}
