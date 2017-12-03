package com.example.weekone_moni.model;

import com.example.weekone_moni.app.MyApplication;
import com.example.weekone_moni.bean.DataBean;
import com.example.weekone_moni.bean.ResultsBean;
import com.example.weekone_moni.dao.DaoSession;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//model接口实现类
public class MyModel {
    /**
     * get请求,动态传值替换请求路径
     * @param callBack
     */
    public void getData(int num,int page,final ModelCallBack callBack){
        //设置接口请求的key值
        Call<DataBean> call = MyApplication.request.get(num, page);

        //发起异步请求
        call.enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                //获取响应的数据，保存在数据库中
                DataBean bean = response.body();
                MyApplication.session.getResultsBeanDao().insertInTx(bean.getResults());

                //请求成功时返回数据
                callBack.onSuccess(bean);
            }

            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {
                //请求失败时返回数据
                callBack.onFailure(new Exception(""));
            }
        });
    }

    /**
     * get请求,无 动态传值请求路径
     * @param callBack
     */
    public void getData(final ModelCallBack callBack){
        //设置接口请求的key值
        Call<DataBean> call = MyApplication.request.get();

        //发起异步请求
        call.enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                //获取响应的数据，保存在数据库中
                DataBean bean = response.body();
                MyApplication.session.getResultsBeanDao().insertInTx(bean.getResults());

                //请求成功时返回数据
                callBack.onSuccess(bean);
            }

            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {
                //请求失败时返回数据
                callBack.onFailure(new Exception(""));
            }
        });
    }

    //进入页面后，进行数据库查询，将查询到到的数据保存到List中
    public List<ResultsBean> getDB(DaoSession session){
        List<ResultsBean> list = session.getResultsBeanDao().loadAll();
        return list;
    }
}
