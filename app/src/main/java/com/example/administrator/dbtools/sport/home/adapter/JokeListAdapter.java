package com.example.administrator.dbtools.sport.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.dbtools.R;
import com.example.administrator.dbtools.base.adapter.RecyclerViewAdapter;
import com.example.administrator.dbtools.base.network.GlideApp;
import com.example.administrator.dbtools.base.util.CommonUtils;
import com.example.administrator.dbtools.sport.home.bean.JokeBeanList;

public class JokeListAdapter extends RecyclerViewAdapter<JokeBeanList> {
    private Context context;

    public JokeListAdapter(Context context) {
        super(R.layout.item_joke);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, JokeBeanList item) {
        if(!TextUtils.isEmpty(item.getTitle())){

            helper.setText(R.id.tvTitle, item.getTitle());
        }else{
            helper.setText(R.id.tvTitle, "");
        }
        if("1".equals(item.getType())){
            helper.setText(R.id.tvText,item.getText());
            helper.setGone(R.id.tvText,true);
        }else{
            helper.setGone(R.id.tvText,false);
        }

//        try {
//            Long longTime = TimeUtils.stringToLong(item.getTime(),"yyyy-MM-dd HH:mm:ss");
//            helper.setText(R.id.tvTime, TimeUtils.getShortTime(longTime));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        helper.setText(R.id.tvTime, item.getCt());
        helper.setText(R.id.tvAuthor,CommonUtils.getAppName(context));
        helper.setText(R.id.im_comment_num ,"");
        helper.setText(R.id.im_click_num ,"");
//        Glide.with(context)
//                .load(item.getThumb())
//                .into((ImageView) helper.getView(R.id.iv));
        String thumb = item.getImg();
        if(!TextUtils.isEmpty(thumb)){
            GlideApp.with(context).load(thumb)
                    .placeholder(R.mipmap.base_defaule_img)
                    .error(R.mipmap.empty)
                    .into((ImageView) helper.getView(R.id.iv1_1));
            helper.setGone(R.id.iv1_1,true);
        }else{
            helper.setGone(R.id.iv1_1,false);

        }


    }
}
