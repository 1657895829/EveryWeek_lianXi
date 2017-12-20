package app.example.videoplayer_quan.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.youth.banner.Banner;
import java.util.ArrayList;
import java.util.List;
import app.example.videoplayer_quan.R;
import app.example.videoplayer_quan.adapter.MyAdapter;
import app.example.videoplayer_quan.bean.HomeBean;
import app.example.videoplayer_quan.presenter.UserPresenter;
import app.example.videoplayer_quan.utils.BannerImagler;
import app.example.videoplayer_quan.view.UserView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 精选页面，展示视频数据
 */
public class JingXuan01_Fragment extends Fragment implements UserView {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    private UserPresenter presenter;
    private List<HomeBean.RetBean.ListBean.ChildListBean> list = new ArrayList<>();
    private List<String> list1 = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jingxuan_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        //实例化 p 层 ，获取数据
        presenter = new UserPresenter((UserView) this);
        presenter.success();
        return view;
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        if (isInMultiWindowMode && Build.VERSION.SDK_INT >= 19) {
            View decorView = getActivity().getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


    @Override
    public void success(HomeBean user) {
        //添加视频的数据
        for (int i = 0; i < user.getRet().getList().size(); i++) {
            int length = user.getRet().getList().get(i).getChildList().size();
            for (int j = 0; j < length; j++) {
                list.add(user.getRet().getList().get(i).getChildList().get(j));
            }
        }

        //获取影片图片，进行无限轮播banner
        for (int i = 0; i < list.size(); i++) {
            list1.add(list.get(i).getPic());
        }
        banner.setImageLoader(new BannerImagler());
        banner.setImages(list1);
        banner.start();

        //设置RecyclerView展示数据的布局管理器以及适配器
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(manager);
        MyAdapter adapter=new MyAdapter(getActivity(),list);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
