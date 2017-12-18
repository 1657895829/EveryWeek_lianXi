package app.example.videoplayer_quan.model;

import java.util.HashMap;
import java.util.Map;
import app.example.videoplayer_quan.bean.Bean;
import app.example.videoplayer_quan.utils.APIFactory;
import app.example.videoplayer_quan.utils.AbstractObserver;

public class BeanModel {
    public void success(final BeanModelCallBack model, String id){
        Map<String,String> map=new HashMap<>();
        map.put("mediaId",id);
        APIFactory.getInstance().get("front/videoDetailApi/videoDetail.do", map,new AbstractObserver<Bean>() {
            @Override
            public void onSuccess(Bean bean) {
                model.success(bean);
            }

            @Override
            public void onFailure(int code) {

            }
        });
    }
}
