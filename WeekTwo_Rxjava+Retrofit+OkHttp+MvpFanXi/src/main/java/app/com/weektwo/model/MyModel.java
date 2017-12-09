package app.com.weektwo.model;

import java.util.concurrent.TimeUnit;
import app.com.weektwo.bean.Bean;
import app.com.weektwo.retrofit.GetDataInterface;
import app.com.weektwo.retrofit_rxjava_okhttp.LoggingInterceptor;
import app.com.weektwo.retrofit_rxjava_okhttp.RetrofitUnitl;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

//model接口实现类
public class MyModel {
    /**
     * 使用Retrofit结合RxJava+Observable的get请求数据，使用@Query注解传递参数
     * @param callBack
     */
    public void getData(int pnum,final ModelCallBack callBack) {
        //使用okhttp请求,添加拦截器时把下面代码解释
        OkHttpClient ok = new OkHttpClient.Builder()
                .connectTimeout(20000, TimeUnit.SECONDS)
                .writeTimeout(20000,TimeUnit.SECONDS)
                .readTimeout(20000,TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .build();

        //使用Retrofit结合RxJava，okhttp封装类的单例模式
        RetrofitUnitl.getInstance("http://api.svipmovie.com",ok)
                .setCreate(GetDataInterface.class)
                .getData("402834815584e463015584e539330016",pnum)
                .subscribeOn(Schedulers.io())               //请求完成后在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())  //最后在主线程中执行

                //进行事件的订阅，使用Consumer实现
                .subscribe(new Consumer<Bean>() {
                    @Override
                    public void accept(Bean bean) throws Exception {
                        //请求成功时返回数据
                        callBack.onSuccess(bean);
                        System.out.println("数据返回："+bean.toString());
                    }
                });
    }

}
