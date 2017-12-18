package app.example.videoplayer_quan.model;

import app.example.videoplayer_quan.bean.UserBean;
import app.example.videoplayer_quan.utils.APIFactory;
import app.example.videoplayer_quan.utils.AbstractObserver;

public class UserModel {
    public void success(final UsermodelCallBack model){
        APIFactory.getInstance().get("front/homePageApi/homePage.do", new AbstractObserver<UserBean>() {
            @Override
            public void onSuccess(UserBean user) {
                model.success(user);
            }

            @Override
            public void onFailure(int code) {

            }
        });
    }
}
