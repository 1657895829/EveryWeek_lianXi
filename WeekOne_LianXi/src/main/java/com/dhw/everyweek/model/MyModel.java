package com.dhw.everyweek.model;

import com.dhw.everyweek.MyApplication;
import com.dhw.everyweek.bean.NewsBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * model接口实现类
 */
public class MyModel {
    /**
     * get请求
     * @param callBack
     */
    public void getData(final ModelCallBack callBack){
        //设置接口请求的key值
        Call<NewsBean> call = MyApplication.request.get("c4479ad58f41e7f78a8fa073d0b1f1b5");

        //发起异步请求
        call.enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                //获取响应的数据，保存在数据库中
                NewsBean bean  = response.body();
                MyApplication.session.getDataBeanDao().insertInTx(bean.getResult().getData());

                //请求成功时返回数据
                callBack.onSuccess(bean);
            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable t) {
                //请求失败时返回数据
                callBack.onFailure(new Exception(""));
            }
        });
    }

    /**
     * post请求
     * @param callBack
     */
    public void postData(final ModelCallBack callBack){
        //设置接口请求的key值
        Call<NewsBean> call = MyApplication.request.post("c4479ad58f41e7f78a8fa073d0b1f1b5");

        //发起异步请求
        call.enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                //获取响应的数据，保存在数据库中
                NewsBean bean = response.body();
                MyApplication.session.getDataBeanDao().insertInTx(bean.getResult().getData());

                //请求成功时返回数据
                callBack.onSuccess(bean);
            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable t) {
                //请求失败时返回数据
                callBack.onFailure(new Exception(""));
            }
        });
    }

}
