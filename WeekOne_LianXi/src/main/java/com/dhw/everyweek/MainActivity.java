package com.dhw.everyweek;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.dhw.everyweek.adapter.MyAdapter;
import com.dhw.everyweek.bean.DataBean;
import com.dhw.everyweek.bean.NewsBean;
import com.dhw.everyweek.presenter.MyPresenter;
import com.dhw.everyweek.view.MyView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

//UI操作，实现view层
public class MainActivity extends AppCompatActivity implements MyView{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private MyPresenter presenter;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //创建Presenter层实例,与view层交互
        presenter = new MyPresenter(this);
        //get请求方式
        presenter.get();

        //设置布局管理器以及布局适配器
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new MyAdapter(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        //遍历查询数据库中的所有数据
        List<DataBean> list = MyApplication.session.getDataBeanDao().loadAll();
        for (DataBean listBean : list){
            System.out.println("查询："+listBean.toString());
        }
    }

    @Override
    public void onSuccess(NewsBean bean) {
        //请求成功时添加数据
        adapter.addData(bean);
    }

    @Override
    public void onFailure(Exception e) {
        System.out.println("数据出错："+e);
    }
}
