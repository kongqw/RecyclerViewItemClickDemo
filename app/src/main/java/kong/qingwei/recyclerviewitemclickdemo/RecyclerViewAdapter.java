package kong.qingwei.recyclerviewitemclickdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by kqw on 2016/3/8.
 * RecyclerViewAdapter
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    public RecyclerViewAdapter() {
    }

    // RecyclerView.ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.text_view);
        }
    }

    // 用来创建新视图（由布局管理器调用）
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(parent.getContext().getApplicationContext(), R.layout.item_recycler_view, null));
    }

    // 用来替换视图的内容（由布局管理器调用）
    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.title.setText("RecyclerView 第 " + position + " 条");
    }

    // 返回数据集的大小（由布局管理器调用）
    @Override
    public int getItemCount() {
        return 10;
    }

}
