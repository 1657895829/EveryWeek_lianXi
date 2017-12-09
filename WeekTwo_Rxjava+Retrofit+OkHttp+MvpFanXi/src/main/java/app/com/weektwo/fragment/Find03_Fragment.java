package app.com.weektwo.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.stone.card.library.CardAdapter;
import com.stone.card.library.CardSlidePanel;
import java.util.ArrayList;
import java.util.List;
import app.com.weektwo.CartDataItem;
import app.com.weektwo.R;
import app.com.weektwo.base.BaseMvpFragment;
import app.com.weektwo.bean.Bean;
import app.com.weektwo.presenter.MyPresenter;
import app.com.weektwo.view.MyView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 发现页Fragment,使用XRecyclerView展示数据
 * 继承自定义BaseMvpFragment，持有p与c层
 */
public class Find03_Fragment extends BaseMvpFragment<MyView, MyPresenter> implements MyView {
    Unbinder unbinder;
    @BindView(R.id.image_slide_panel)
    CardSlidePanel imageSlidePanel;
    @BindView(R.id.notify_change)
    Button change;
    private List<Bean.RetBean.ListBean> list;
    private List<CartDataItem> dataList = new ArrayList<>();
    private CardSlidePanel.CardSwitchListener cardSwitchListener;
    private String imagePaths[] = {}; // 图片资源
    private String titles[] = {};      //标题
    private String desc[]={};          //描述

    //声明presenter层,与view层交互
    @Override
    public MyPresenter initPresenter() {
        return new MyPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        p.get(1);
    }

    @Override
    public void onSuccess(Bean bean) {
        Bean.RetBean ret = bean.getRet();
        titles = new String[ret.getList().size()];
        imagePaths = new String[ret.getList().size()];
        desc = new String[ret.getList().size()];
        for (int i = 0; i < ret.getList().size(); i++) {
            list = ret.getList();
            titles[i] = list.get(i).getTitle();
            imagePaths[i] = list.get(i).getPic();
            desc[i] = list.get(i).getDescription();
            System.out.print("：：：："+desc[i]);
        }
        initView();

        System.out.println("解析：" + bean.getRet().getList().toString());
    }

    //设置加载布局
    private void initView() {
        //1. 设置卡片左右滑动监听
        cardSwitchListener = new CardSlidePanel.CardSwitchListener() {
            @Override
            public void onShow(int index) {
                Log.d("Card", "正在显示-" + dataList.get(index).title);
            }

            @Override
            public void onCardVanish(int index, int type) {
                Log.d("Card", "正在消失-" + dataList.get(index).title + " 消失type=" + type);
            }
        };
        imageSlidePanel.setCardSwitchListener(cardSwitchListener);

        // 2. 绑定Adapter
        imageSlidePanel.setAdapter(new CardAdapter() {
            @Override
            public int getLayoutId() {
                return R.layout.card_item;
            }

            @Override
            public int getCount() {
                return dataList.size();
            }

            @Override
            public void bindView(View view, int index) {
                //绑定布局
                Object tag = view.getTag();
                ViewHolder holder;
                if (tag != null){
                    holder = (ViewHolder) tag;
                }else {
                    holder = new ViewHolder(view);
                    view.setTag(holder);
                }
                holder.bindData(dataList.get(index));
            }

            @Override
            public Object getItem(int index) {
                return dataList.get(index);
            }

            @Override
            public Rect obtainDraggableArea(View view) {
                // 可滑动区域定制，该函数只会调用一次
                View contentView = view.findViewById(R.id.card_item_content);
                View topLayout = view.findViewById(R.id.card_top_layout);
                View bottomLayout = view.findViewById(R.id.card_bottom_layout);
                int left = view.getLeft() + contentView.getPaddingLeft() + topLayout.getPaddingLeft();
                int right = view.getRight() - contentView.getPaddingRight() - topLayout.getPaddingRight();
                int top = view.getTop() + contentView.getPaddingTop() + topLayout.getPaddingTop();
                int bottom = view.getBottom() - contentView.getPaddingBottom() - bottomLayout.getPaddingBottom();
                return new Rect(left, top, right, bottom);
            }
        });

        // 延时改变数据
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                prepareDataList();
                imageSlidePanel.getAdapter().notifyDataSetChanged();
            }
        }, 500);

        // 3.  数据发送改变时调用
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendDataList();
                imageSlidePanel.getAdapter().notifyDataSetChanged();
            }
        });
    }

    private void prepareDataList() {
        for (int i = 0; i < list.size(); i++) {
            CartDataItem itemUtil = new CartDataItem();
            itemUtil.title = titles[i];
            itemUtil.imagePath = imagePaths[i];
            itemUtil.desc = desc[i];

            itemUtil.likeNum = (int) (Math.random() * 10);
            itemUtil.imageNum = (int) (Math.random() * 6);
            itemUtil.descNum = (int) (Math.random() * 12);
            dataList.add(itemUtil);
        }
    }

    //
    private void appendDataList() {
        for (int i = 0; i < list.size() ; i++) {
            CartDataItem itemUtil = new CartDataItem();
            itemUtil.title = "标题";
            itemUtil.imagePath = imagePaths[28];
            itemUtil.desc=" 描述";

            itemUtil.likeNum = (int) (Math.random() * 10);
            itemUtil.imageNum = (int) (Math.random() * 6);
            itemUtil.descNum =  (int) (Math.random() * 12);
            dataList.add(itemUtil);
        }
    }

    class ViewHolder {
        ImageView imageView;
        TextView userNameTv;
        TextView imageNumTv;
        TextView  biaotitV;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.card_image_view);
            biaotitV =   (TextView) view.findViewById(R.id.card_desc);
            userNameTv = (TextView) view.findViewById(R.id.card_user_name);
            imageNumTv = (TextView) view.findViewById(R.id.card_pic_num);
        }

        //
        public void bindData(CartDataItem itemData) {
            Glide.with(getActivity()).load(itemData.imagePath).into(imageView);
            userNameTv.setText(itemData.title);
            imageNumTv.setText(itemData.imageNum + "");
            biaotitV.setText(itemData.desc);
            for (int i=0;i<28;i++){
                biaotitV.setText(list.get(i).getDescription());
            }
        }
    }

    @Override
    public void onFailure(Exception e) {
        System.out.println("数据出错：" + e);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
