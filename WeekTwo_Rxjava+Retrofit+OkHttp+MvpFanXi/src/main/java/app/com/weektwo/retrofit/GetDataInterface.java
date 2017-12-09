package app.com.weektwo.retrofit;

import app.com.weektwo.bean.Bean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 网络接口数据的请求类
 * 接口： http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=402834815584e463015584e539330016&pnum=1
 */
public interface GetDataInterface {
    /*
     * 使用Observable被观察者模式下的的get请求数据 @Query注解传递参数
     */
    @GET("front/columns/getVideoList.do")
    Observable<Bean> getData(@Query("catalogId") String catalogId, @Query("pnum") int pnum);

}
