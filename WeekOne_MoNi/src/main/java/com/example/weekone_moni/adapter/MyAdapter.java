package com.example.weekone_moni.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.weekone_moni.R;
import com.example.weekone_moni.bean.DataBean;
import com.example.weekone_moni.bean.ResultsBean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

//RecyclerView展示数据适配器
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<ResultsBean> list;

    public MyAdapter(Context context) {
        this.context = context;
    }

    //添加数据
    public void addData(DataBean bean) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.addAll(bean.getResults());
        notifyDataSetChanged();
    }

    //更新数据库并刷新列表数据
    public void addList(List bean){
        if (list == null) {
            list = new ArrayList<>();
        }
        list.addAll(bean);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //添加布局视图
        View view = View.inflate(context, R.layout.adapter, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //设置参数数据
        holder.draweeView.setImageURI(list.get(position).getUrl());
        holder.title.setText(list.get(position).getWho());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0: list.size();
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
