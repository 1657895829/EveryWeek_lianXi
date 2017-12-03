package com.example.weekone_moni.presenter;

import com.example.weekone_moni.app.MyApplication;
import com.example.weekone_moni.bean.DataBean;
import com.example.weekone_moni.bean.ResultsBean;
import com.example.weekone_moni.model.ModelCallBack;
import com.example.weekone_moni.model.MyModel;
import com.example.weekone_moni.view.MyView;
import java.util.List;

// Presenter层，进行view层与model数据的交互
public class MyPresenter {
    private MyView myView;
    private MyModel myModel;

    //构造方法使三层交互
    public MyPresenter(MyView myView) {
        this.myView = myView;
        this.myModel = new MyModel();
    }

    //获取model层保存的数据,以集合的形式返回给view层
    public List<ResultsBean> getDB(){
        List<ResultsBean> list = myModel.getDB(MyApplication.session);
        return list;
    }

    /**
     * get请求数据交互,动态传值替换请求路径
     */
    public void get(int num,int page){
        myModel.getData(num,page,new ModelCallBack() {
            @Override
            public void onSuccess(DataBean bean) {
                //数据交互时，为防止内存泄露，设置view层数据为空
                if (myView != null){
                    myView.onSuccess(bean);
                }
            }

            @Override
            public void onFailure(Exception e) {
                //数据交互时，为防止内存泄露，设置view层数据为空
                if (myView != null){
                    myView.onFailure(new Exception("e"));
                }
            }
        });
    }

    /**
     * get请求数据交互, 无动态传值请求路径
     */
    public void get(){
        myModel.getData(new ModelCallBack() {
            @Override
            public void onSuccess(DataBean bean) {
                //数据交互时，为防止内存泄露，设置view层数据为空
                if (myView != null){
                    myView.onSuccess(bean);
                }
            }

            @Override
            public void onFailure(Exception e) {
                //数据交互时，为防止内存泄露，设置view层数据为空
                if (myView != null){
                    myView.onFailure(new Exception("e"));
                }
            }
        });
    }

}
