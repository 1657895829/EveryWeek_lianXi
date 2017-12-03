package com.example.weekone_moni.app;

import android.app.Application;

import com.example.weekone_moni.dao.DaoMaster;
import com.example.weekone_moni.dao.DaoSession;
import com.example.weekone_moni.retrofit.GetDataInterface;
import com.facebook.drawee.backends.pipeline.Fresco;
import org.greenrobot.greendao.database.Database;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * 初始化全局配置应用类
 */
public class MyApplication extends Application {
    //设置公共变量
    public static DaoSession session;
    public static GetDataInterface request;

    @Override
    public void onCreate() {
        super.onCreate();
        //1. Fresco 图片加载
        Fresco.initialize(this);

        //2. Retrofit 网络请求
            //构建 Retrofit类，初始化参数
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://gank.io")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            //创建网络请求接口实例
            request = retrofit.create(GetDataInterface.class);

        //3. GreenDao数据库
       DaoMaster.DevOpenHelper data = new DaoMaster.DevOpenHelper(this, "data");
       Database database = data.getWritableDb();session = new DaoMaster(database).newSession();
    }
}
