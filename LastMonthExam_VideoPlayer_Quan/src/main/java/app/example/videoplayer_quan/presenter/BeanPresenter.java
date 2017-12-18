package app.example.videoplayer_quan.presenter;

import app.example.videoplayer_quan.bean.Bean;
import app.example.videoplayer_quan.model.BeanModel;
import app.example.videoplayer_quan.model.BeanModelCallBack;
import app.example.videoplayer_quan.view.BeanView;

public class BeanPresenter {
    private BeanModel model;
    private BeanView view;

    public BeanPresenter(BeanView view) {
        this.view = view;
        model=new BeanModel();
    }
    public void success(String id){
        model.success(new BeanModelCallBack() {
            @Override
            public void success(Bean bean) {
                view.success(bean);
            }
        },id);
    }
}
