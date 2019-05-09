package com.example.administrator.dbtools.base.network;



import com.example.administrator.dbtools.base.App;
import com.example.administrator.dbtools.base.AppConfig;
import com.example.administrator.dbtools.base.util.DateUtils;
import com.example.administrator.dbtools.base.util.sqlite.SQLiteKey;
import com.example.administrator.dbtools.base.util.sqlite.SQLiteUtil;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Description: 网络请求client
 */
public class ZClient {
    // 网络请求超时时间值(s)
    private static final int DEFAULT_TIMEOUT = 10;
    // retrofit实例
    private Retrofit retrofit;

    private ZClient() {
        // 创建一个OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置网络请求超时时间
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        // 失败后尝试重新请求
        builder.retryOnConnectionFailure(true);
        //设置缓存
        File cacheDir = new File(App.getApp().getCacheDir(), "response");
        //缓存的最大尺寸10m
        Cache cache = new Cache(cacheDir, 1024 * 1024 * 10);
        builder.cache(cache);
        builder.addNetworkInterceptor(new CacheInterceptor());
        // 打印参数
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        //适配多小米小庄多BaseUrl
        builder.addInterceptor(new ZMultiBaseUrlInterceptor());
        //添加默认参数
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalUrl = original.url();
                HttpUrl url = originalUrl.newBuilder()
                        .addQueryParameter("showapi_appid", AppConfig.APP_ID)
                        .addQueryParameter("showapi_sign", AppConfig.APP_SIGN)
                        .addQueryParameter("showapi_timestamp", DateUtils.currentFormatDate("yyyyMMddhhmmss")).build();
                Request request = original.newBuilder()
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .header("Content-Type", "application/json")
                        .header("token", SQLiteUtil.getEncryptedString(SQLiteKey.TOKEN))
                        .url(url)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(URLs.BASE_URL_MUZI)
                .client(builder.build())
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 调用单例对象
     */
    private static ZClient getInstance() {
        return RDClientInstance.instance;
    }

    /**
     * 创建单例对象
     */
    private static class RDClientInstance {
        static ZClient instance = new ZClient();
    }

    // service
    private static TreeMap<String, Object> serviceMap;

    private static TreeMap<String, Object> getServiceMap() {
        if (serviceMap == null)
            serviceMap = new TreeMap<>();
        return serviceMap;
    }

    /**
     * @return 指定service实例
     */
    public static <T> T getService(Class<T> clazz) {
        if (getServiceMap().containsKey(clazz.getSimpleName())) {
            return (T) getServiceMap().get(clazz.getSimpleName());
        }
        T service = ZClient.getInstance().retrofit.create(clazz);
        getServiceMap().put(clazz.getSimpleName(), service);
        return service;
    }
}
