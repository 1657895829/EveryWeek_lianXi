package app.example.videoplayer_quan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import org.greenrobot.eventbus.EventBus;
import java.util.List;
import app.example.videoplayer_quan.R;
import app.example.videoplayer_quan.SecondActivity;
import app.example.videoplayer_quan.bean.UserBean;
import app.example.videoplayer_quan.utils.Eventbusmessage;
import butterknife.BindView;
import butterknife.ButterKnife;

//适配器类 展示 RecyclerView数据
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<UserBean.RetBean.ListBean.ChildListBean> list;

    public MyAdapter(Context context, List<UserBean.RetBean.ListBean.ChildListBean> list) {
        this.context = context;
        this.list = list;
    }

    //设置布局
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.adapter, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //加载布局
        holder.iv.setImageURI(list.get(position).getPic());
        holder.tv.setText(list.get(position).getTitle());

        //参数的点击事件
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new Eventbusmessage(list.get(position).getDataId(), list.get(position).getTitle()));
                context.startActivity(new Intent(context, SecondActivity.class));
            }
        });
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new Eventbusmessage(list.get(position).getDataId(), list.get(position).getTitle()));
                context.startActivity(new Intent(context, SecondActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        SimpleDraweeView iv;
        @BindView(R.id.tv)
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
