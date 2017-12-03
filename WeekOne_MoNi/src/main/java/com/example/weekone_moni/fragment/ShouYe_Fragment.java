package com.example.weekone_moni.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.weekone_moni.R;
import com.example.weekone_moni.adapter.MyAdapter;
import com.example.weekone_moni.app.MyApplication;
import com.example.weekone_moni.bean.DataBean;
import com.example.weekone_moni.bean.ResultsBean;
import com.example.weekone_moni.presenter.MyPresenter;
import com.example.weekone_moni.util.NetConnectionUtil;
import com.example.weekone_moni.view.MyView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页
 */
public class ShouYe_Fragment extends Fragment implements MyView{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private MyAdapter adapter;
    private MyPresenter presenter;
    int num = 10,page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shouye_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化EventBus,注册EventBus
        EventBus.getDefault().register(this);

        //创建Presenter层实例,与view层交互
        presenter = new MyPresenter(this);

        //设置布局管理器以及布局适配器
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new MyAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        //执行网络请求自动解析数据，如果数据不为空的情况则更新List
        List<ResultsBean> beanList = presenter.getDB();
        if (beanList == null || beanList.size() == 0){
            //动态传值替换请求路径：get请求方式
            presenter.get(num,page);

            //无动态传值请求路径：get请求方式
            //presenter.get();
        }else {

            //如果数据为空，就从数据库中查数据
            adapter.addList(beanList);
        }

        //遍历查询数据库中的所有数据
        List<ResultsBean> list = MyApplication.session.getResultsBeanDao().loadAll();
        for (ResultsBean bean : list){
            System.out.println("查询："+bean.toString());
        }
    }

    //在UI主线程，注册粘性订阅事件，进行黏性事件处理函数
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    //判断网络，请求网络数据
    public void getEventBus(String flag){
        if (flag.equals("true")){
            Toast.makeText(getActivity(),"网络状态良好,访问网络数据正常",Toast.LENGTH_SHORT).show();

            //动态传值替换请求路径：get请求方式
            presenter.get(num,page);

            //无动态传值请求路径：get请求方式
            //presenter.get();
        }else {
            NetConnectionUtil.setNetConnectionWork(getActivity());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //在onDestory()方法中取消订阅：防止内存溢出
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSuccess(DataBean bean) {
        //请求成功时添加数据
        adapter.addData(bean);
    }

    @Override
    public void onFailure(Exception e) {
        System.out.println("数据出错："+e);
    }
}
