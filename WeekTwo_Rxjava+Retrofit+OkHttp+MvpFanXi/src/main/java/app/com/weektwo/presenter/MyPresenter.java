package app.com.weektwo.presenter;

import app.com.weektwo.base.BasePresenter;
import app.com.weektwo.bean.Bean;
import app.com.weektwo.model.ModelCallBack;
import app.com.weektwo.model.MyModel;
import app.com.weektwo.view.MyView;

// Presenter层，进行view层与model数据的交互
public class MyPresenter extends BasePresenter<MyView> {
    private MyModel myModel;
    public MyPresenter() {
        this.myModel = new MyModel();
    }

    /**
     * 简单使用Retrofit结合RxJava的get请求数据, 使用@QueryMap注解传递集合参数
     */
    public void get(int pnum){
        myModel.getData(pnum,new ModelCallBack() {
            @Override
            public void onSuccess(Bean bean) {
                //数据交互时，为防止内存泄露，设置view层数据为空
                if (view != null){
                    view.onSuccess(bean);
                }
            }

            @Override
            public void onFailure(Exception e) {
                //数据交互时，为防止内存泄露，设置view层数据为空
                if (view != null){
                    view.onFailure(new Exception("e"));
                }
            }
        });
    }

    //取消p层与v层的绑定，防止内存泄露
    public void detach(){
        this.view = null;
    }

}
