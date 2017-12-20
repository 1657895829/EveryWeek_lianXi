package app.example.videoplayer_quan.presenter;

import app.example.videoplayer_quan.bean.HomeBean;
import app.example.videoplayer_quan.model.UserModel;
import app.example.videoplayer_quan.model.UsermodelCallBack;
import app.example.videoplayer_quan.view.UserView;

public class UserPresenter {
    private UserModel model;
    private UserView view;

    public UserPresenter(UserView view) {
        this.view = view;
        model=new UserModel();
    }
    public void success(){
        model.success(new UsermodelCallBack() {
            @Override
            public void success(HomeBean user) {
                view.success(user);
            }
        });
    }
}
