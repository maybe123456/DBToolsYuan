package com.example.administrator.dbtools.sport.home.fragment;


import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.administrator.dbtools.R;
import com.example.administrator.dbtools.base.BaseFragment;
import com.example.administrator.dbtools.base.network.ZCallback;
import com.example.administrator.dbtools.base.network.ZClient;
import com.example.administrator.dbtools.base.network.ZResponse;
import com.example.administrator.dbtools.base.util.ToastUtil;
import com.example.administrator.dbtools.databinding.FragmentPoemBinding;
import com.example.administrator.dbtools.sport.SportService;
import com.example.administrator.dbtools.sport.home.bean.PoemBean;
import com.example.administrator.dbtools.sport.home.bean.SpinnerBean;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.SimpleSpinnerTextFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class PoemFragment extends BaseFragment<FragmentPoemBinding> {

    private String num = "5";
    private String yayuntype = "1";

    @Override
    public int getLayout() {
        return R.layout.fragment_poem;
    }

    @Override
    public void initView() {
        setupTintedWithCustomClass();
        setupDefault();
    }


    private void setupDefault() {
//        List<String> dataset = new LinkedList<>(Arrays.asList("五言", "七言"));
//        binding.numSpinner.attachDataSource(dataset);
//        binding.numSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                Toast.makeText(mContext, "Selected: " + item, Toast.LENGTH_SHORT).show();
//            }
//        });

//        List<String> dataYayun = new LinkedList<>(Arrays.asList("五言", "七言"));
//        binding.yayunSpinner.attachDataSource(dataYayun);
//        binding.yayunSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                Toast.makeText(mContext, "Selected: " + item, Toast.LENGTH_SHORT).show();
//            }
//        });


        final List<SpinnerBean> spinnerBeans = new ArrayList<>();

        spinnerBeans.add(new SpinnerBean("双句一押", "1"));
        spinnerBeans.add(new SpinnerBean("双句押韵", "2"));
        spinnerBeans.add(new SpinnerBean("一三四押", "3"));

        SimpleSpinnerTextFormatter textFormatter = new SimpleSpinnerTextFormatter() {
            @Override
            public Spannable format(Object item) {
                SpinnerBean spinnerBeans = (SpinnerBean) item;
                return new SpannableString(spinnerBeans.getNameSpinner());
            }
        };

        binding.yayunSpinner.setSpinnerTextFormatter(textFormatter);
        binding.yayunSpinner.setSelectedTextFormatter(textFormatter);
        binding.yayunSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SpinnerBean spinnerBean = (SpinnerBean) binding.yayunSpinner.getSelectedItem(); //parent.getItemAtPosition(position).toString();
//                Toast.makeText(mContext, "Selected: " + spinnerBean.getCodeSpinner(), Toast.LENGTH_SHORT).show();
                yayuntype = spinnerBean.getCodeSpinner();
            }
        });
        binding.yayunSpinner.attachDataSource(spinnerBeans);


    }

    private void setupTintedWithCustomClass() {
        List<SpinnerBean> spinnerBeans = new ArrayList<>();

        spinnerBeans.add(new SpinnerBean("五言", "5"));
        spinnerBeans.add(new SpinnerBean("七言", "7"));

        SimpleSpinnerTextFormatter textFormatter = new SimpleSpinnerTextFormatter() {
            @Override
            public Spannable format(Object item) {
                SpinnerBean spinnerBeans = (SpinnerBean) item;
                return new SpannableString(spinnerBeans.getNameSpinner());
            }
        };

        binding.numSpinner.setSpinnerTextFormatter(textFormatter);
        binding.numSpinner.setSelectedTextFormatter(textFormatter);
        binding.numSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SpinnerBean spinnerBean = (SpinnerBean) binding.numSpinner.getSelectedItem(); //parent.getItemAtPosition(position).toString();
//                Toast.makeText(mContext, "Selected: " + spinnerBean.getCodeSpinner(), Toast.LENGTH_SHORT).show();
                num = spinnerBean.getCodeSpinner();
            }
        });
        binding.numSpinner.attachDataSource(spinnerBeans);
    }

    @Override
    public void initListener() {
        binding.tvGetPoem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_get_poem:
                getPoem();
                break;
        }
    }

    private void getPoem() {
        String etText = binding.etInputWord.getText().toString();
        if(TextUtils.isEmpty(etText)){
            ToastUtil.toast("请输入句子");
            return;
        }

        ZClient.getService(SportService.class).getPoemList(num,"1",yayuntype,etText).enqueue(new ZCallback<ZResponse<PoemBean>>(this) {
            @Override
            protected void onSuccess(ZResponse<PoemBean> zResponse) {
                PoemBean poemBean =  zResponse.getShowapi_res_body();
                if(null != poemBean.getList()){
                    binding.tvPoem.setText(poemBean.getList()[0]);
                }else{
                    binding.tvPoem.setText("不能生成诗句");
                }
            }
        });

    }
}
