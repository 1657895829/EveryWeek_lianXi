package com.example.weekone_moni.retrofit;

import com.example.weekone_moni.bean.DataBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//请求网络数据接口类：http://gank.io/api/data/福利/10/1
public interface GetDataInterface {
    /**
     * get请求,通过注解的方式设置参数
     * @Path 注解：动态替换路径
     * @param num
     * @param page
     * @return
     */
    @GET("/api/data/福利/{num}/{page}")
    Call<DataBean> get(@Path("num") int num, @Path("page") int page);


    /**
     * get请求 无动态路径替换
     * @return
     */
    @GET("/api/data/福利/10/1")
    Call<DataBean> get();
}
