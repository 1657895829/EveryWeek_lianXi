package com.dhw.everyweek.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dhw.everyweek.R;
import com.dhw.everyweek.SecondActivity;
import com.dhw.everyweek.bean.DataBean;
import com.dhw.everyweek.bean.EventBusBean;
import com.dhw.everyweek.bean.NewsBean;
import com.facebook.drawee.view.SimpleDraweeView;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

//RecyclerView展示数据适配器
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<DataBean> list;

    public MyAdapter(Context context) {
        this.context = context;
    }

    //添加数据
    public void addData(NewsBean bean) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.addAll(bean.getResult().getData());
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //添加布局视图
        View view = View.inflate(context, R.layout.adapter, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.draweeView.setImageURI(list.get(position).getThumbnail_pic_s());
        holder.title.setText(list.get(position).getTitle());

        //点击跳转传值
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //携带实体类数据，实现点击跳转，发送黏性事件
                EventBusBean busBean = new EventBusBean(list.get(position).getThumbnail_pic_s(), list.get(position).getTitle());

                EventBus.getDefault().postSticky(busBean);
                context.startActivity(new Intent(context, SecondActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.draweeView)
        SimpleDraweeView draweeView;
        @BindView(R.id.title)
        TextView title;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
