package com.dhw.everyweek;

import android.app.Application;
import com.dhw.everyweek.dao.DaoMaster;
import com.dhw.everyweek.dao.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;
import org.greenrobot.greendao.database.Database;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 用于全局配置初始化异步加载类
 */
public class MyApplication extends Application {
    //设置公共变量
    public static DaoSession session;
    public static GetDataInterface request;

    @Override
    public void onCreate() {
        super.onCreate();
        //1.  用于全局配置初始化Fresco 图片加载
        Fresco.initialize(this);

        //2.  用于全局配置初始化Retrofit 网络请求
            //构建Retrofit类，初始化参数
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://v.juhe.cn")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            //创建网络请求接口实例
            request = retrofit.create(GetDataInterface.class);

        //3.  用于全局配置初始化GreenDao数据库
        DaoMaster.DevOpenHelper database = new DaoMaster.DevOpenHelper(this, "database");
        Database db = database.getWritableDb();             //获取能读能写的数据库
        session = new DaoMaster(db).newSession();
    }
}
