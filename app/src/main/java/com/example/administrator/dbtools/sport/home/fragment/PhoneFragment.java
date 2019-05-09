package com.example.administrator.dbtools.sport.home.fragment;


import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.example.administrator.dbtools.R;
import com.example.administrator.dbtools.base.BaseFragment;
import com.example.administrator.dbtools.base.network.ZCallback;
import com.example.administrator.dbtools.base.network.ZClient;
import com.example.administrator.dbtools.base.network.ZResponse;
import com.example.administrator.dbtools.base.util.CommonUtils;
import com.example.administrator.dbtools.base.util.ToastUtil;
import com.example.administrator.dbtools.databinding.FragmentPhoneBinding;
import com.example.administrator.dbtools.databinding.FragmentPoemBinding;
import com.example.administrator.dbtools.sport.SportService;
import com.example.administrator.dbtools.sport.home.bean.PhoneBean;
import com.example.administrator.dbtools.sport.home.bean.PoemBean;
import com.example.administrator.dbtools.sport.home.bean.SpinnerBean;

import org.angmarch.views.SimpleSpinnerTextFormatter;

import java.util.ArrayList;
import java.util.List;


public class PhoneFragment extends BaseFragment<FragmentPhoneBinding> {


    @Override
    public int getLayout() {
        return R.layout.fragment_phone;
    }

    @Override
    public void initView() {

    }



    @Override
    public void initListener() {
        binding.tvGetPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_get_phone:
                getPoem();
                break;
        }
    }

    private void getPoem() {
        String numPhone = binding.etInputNum.getText().toString();

        if(!CommonUtils.checkPhone(numPhone)){
            ToastUtil.toast("请输入正确的手机号码！");
        }else{
            ZClient.getService(SportService.class).getPhoneLoction(numPhone).enqueue(new ZCallback<ZResponse<PhoneBean>>(this) {
                @Override
                protected void onSuccess(ZResponse<PhoneBean> zResponse) {
                    PhoneBean poemBean =  zResponse.getShowapi_res_body();
//运营商：\n省份：\n城市：\n邮编：\n区号：\n
                    binding.tvPhone.setText("运营商："+poemBean.getName()+"\n"
                            +"省份："+poemBean.getProv()+"\n"+"城市："+poemBean.getCity()+"\n"
                            + "城市邮编："+poemBean.getCityCode()+"\n"
                            + "区号："+poemBean.getAreaCode()+"\n");
                }
            });
        }
    }
}
