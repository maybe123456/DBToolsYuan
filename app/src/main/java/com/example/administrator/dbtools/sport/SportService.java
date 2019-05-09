package com.example.administrator.dbtools.sport;


import com.example.administrator.dbtools.base.network.ZResponse;
import com.example.administrator.dbtools.sport.home.bean.ChannerlKey;
import com.example.administrator.dbtools.sport.home.bean.Football;
import com.example.administrator.dbtools.sport.home.bean.JokeBean;
import com.example.administrator.dbtools.sport.home.bean.JokeBeanList;
import com.example.administrator.dbtools.sport.home.bean.PhoneBean;
import com.example.administrator.dbtools.sport.home.bean.PoemBean;
import com.example.administrator.dbtools.sport.home.bean.SickMainBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Michael on 2018/10/27 19:50 (星期六)
 */
public interface SportService {


    /**
     * 首页title 类型
     */
    @Headers({"BaseUrl:zh"})
    @POST("news/newstypes")
    Call<ZResponse<List<ChannerlKey>>> getSearchKeys();

    /**
     * 首页列表
     */
    @Headers({"BaseUrl:zh"})//@POST("home/Moneyonline/fishlist")
    @POST("news/newslist")
    Call<ZResponse<List<Football>>> getNewsSpotrList(@Query("type") int type, @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 首页列表
     * num 五言 num=5 七言num=7 ; type type=1 藏头
     * type=2 藏尾
     * type=3 藏中
     * type=4 递增
     * type=5 递减
     * yayuntype yayuntype=1 双句一压
     * yayuntype=2 双句押韵
     * yayuntype=3 一三四押
     * key 藏头诗中要包括的句子，最多八个子。
     */
    @Headers({"BaseUrl:zh"})//@POST("home/Moneyonline/fishlist")
    @POST("950-1/")
    Call<ZResponse<PoemBean>> getPoemList(@Query("num") String num, @Query("type") String type, @Query("yayuntype") String yayuntype, @Query("key") String key);


    /**
     *
     * @param num
     * @return
     */
    @Headers({"BaseUrl:zh"})
    @POST("6-1/")
    Call<ZResponse<PhoneBean>> getPhoneLoction(@Query("num") String num);

    /**
     *
     * @param page
     * @param maxResult
     * @return
     */
    @Headers({"BaseUrl:zh"})
    @POST("341-2/")
    Call<ZResponse<JokeBean>> getJokeList(@Query("page") int page, @Query("maxResult") int maxResult);

    /**
     *
     * @param page
     * @param maxResult
     * @return
     */
    @Headers({"BaseUrl:zh"})
    @POST("341-1/")
    Call<ZResponse<JokeBean>> getJokeTextList(@Query("page") int page, @Query("maxResult") int maxResult);

    /**
     *
     * @return
     */
    @Headers({"BaseUrl:zh"})
    @POST("546-1/")
    Call<ZResponse<SickMainBean>> getSickList();

    /**
     *
     * @return
     */
    @Headers({"BaseUrl:zh"})
    @POST("546-2/")
    Call<ZResponse<JokeBeanList>> getSickKeyList(@Query("subTypeId") int subTypeId, @Query("page") int page);

}
