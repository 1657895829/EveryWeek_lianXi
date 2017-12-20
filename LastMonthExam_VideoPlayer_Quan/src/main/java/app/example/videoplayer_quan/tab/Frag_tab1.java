package app.example.videoplayer_quan.tab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;
import app.example.videoplayer_quan.R;
import app.example.videoplayer_quan.adapter.MyAdapter2;
import app.example.videoplayer_quan.bean.HomeBean;
import app.example.videoplayer_quan.bean.VideoBean;
import app.example.videoplayer_quan.presenter.BeanPresenter;
import app.example.videoplayer_quan.presenter.UserPresenter;
import app.example.videoplayer_quan.utils.Eventbusmessage;
import app.example.videoplayer_quan.view.BeanView;
import app.example.videoplayer_quan.view.UserView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//简介页面
public class Frag_tab1 extends Fragment implements UserView,BeanView {
    MyAdapter2 adapter2;
    List<HomeBean.RetBean.ListBean.ChildListBean> listBeans = new ArrayList<HomeBean.RetBean.ListBean.ChildListBean>();
    @BindView(R.id.daoyuan)
    TextView daoyan;
    @BindView(R.id.zhuyan)
    TextView zhuyan;
    @BindView(R.id.recylerview)
    RecyclerView recylerview;
    Unbinder unbinder;
    private View view;
    private BeanPresenter beanPresenter;
    private UserPresenter userPresenter;
    private String str = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.tab_layout1, null);
        unbinder = ButterKnife.bind(this, view);

        //注册EventBus
        EventBus.getDefault().register(this);

        //实例化p层，获取首页数据
        beanPresenter = new BeanPresenter((BeanView) this);
        beanPresenter.success(str);

        //实例化 p 层 ，获取详情数据
        userPresenter = new UserPresenter((UserView) this);
        userPresenter.success();

        return view;
    }

    @Subscribe(sticky = true)
    public void onmessage(Eventbusmessage eventbusmessage) {
        str = eventbusmessage.getPosition();
    }

    @Override
    public void success(VideoBean videoBean) {

        //设置影片详情
        daoyan.setText("导演：" + videoBean.getRet().getDirector());
        zhuyan.setText("演员：" + videoBean.getRet().getActors());
        daoyan.setTextColor(Color.WHITE);
        zhuyan.setTextColor(Color.WHITE);
    }



    @Override
    public void success(HomeBean user) {
        //添加数据
        for (int i = 0; i < user.getRet().getList().size(); i++) {
            int len = user.getRet().getList().get(i).getChildList().size();
            for (int j = 0; j < len; j++) {
                listBeans.add(user.getRet().getList().get(i).getChildList().get(j));
            }
        }

        //条目的点击回调事件
        adapter2 = new MyAdapter2(getActivity(), listBeans);
        adapter2.setOnItemClickListener(new MyAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        //设置布局管理器以及数据适配器
        GridLayoutManager gl = new GridLayoutManager(getActivity(), 3);
        recylerview.setLayoutManager(gl);
        recylerview.setAdapter(adapter2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
