package app.example.videoplayer_quan.model;

import java.util.HashMap;
import java.util.Map;
import app.example.videoplayer_quan.bean.VideoBean;
import app.example.videoplayer_quan.utils.APIFactory;
import app.example.videoplayer_quan.utils.AbstractObserver;

public class BeanModel {
    public void success(final BeanModelCallBack model, String id){
        Map<String,String> map=new HashMap<>();
        map.put("mediaId",id);
        APIFactory.getInstance().get("front/videoDetailApi/videoDetail.do", map,new AbstractObserver<VideoBean>() {
            @Override
            public void onSuccess(VideoBean videoBean) {
                model.success(videoBean);
            }

            @Override
            public void onFailure(int code) {

            }
        });
    }
}
