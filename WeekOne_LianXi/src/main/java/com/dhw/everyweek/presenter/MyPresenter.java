package com.dhw.everyweek.presenter;

import com.dhw.everyweek.bean.NewsBean;
import com.dhw.everyweek.model.ModelCallBack;
import com.dhw.everyweek.model.MyModel;
import com.dhw.everyweek.view.MyView;

/**
 * Presenter层，进行view层与model数据的交互
 */
public class MyPresenter {
    private MyView  myView;
    private MyModel myModel;

    public MyPresenter(MyView myView) {
        this.myView = myView;
        this.myModel = new MyModel();
    }

    /**
     * get请求数据交互
     */
    public void get(){
        myModel.getData(new ModelCallBack() {
            @Override
            public void onSuccess(NewsBean bean) {
                //数据交互时，为防止内存泄露，设置view层数据为空
                if (myView != null){
                    myView.onSuccess(bean);
                }
            }

            @Override
            public void onFailure(Exception e) {
                //数据交互时，为防止内存泄露，设置view层数据为空
                if (myView != null){
                    myView.onFailure(e);
                }
            }
        });
    }

    /**
     * post请求数据交互
     */
    public void post(){
        myModel.postData(new ModelCallBack() {
            @Override
            public void onSuccess(NewsBean bean) {
                //数据交互时，为防止内存泄露，设置view层数据为空
                if (myView != null){
                    myView.onSuccess(bean);
                }
            }

            @Override
            public void onFailure(Exception e) {
                //数据交互时，为防止内存泄露，设置view层数据为空
                if (myView != null){
                    myView.onFailure(e);
                }
            }
        });
    }
}
