package kong.qingwei.recyclerviewitemclickdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements KqwRecyclerView.OnItemClickListener, KqwRecyclerView.OnItemLongClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KqwRecyclerView recyclerView = (KqwRecyclerView) findViewById(R.id.recyclerView);
        // 如果数据的填充不会改变RecyclerView的布局大小，那么这个设置可以提高RecyclerView的性能
        recyclerView.setHasFixedSize(true);
        // 设置这个RecyclerView是线性布局
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.setOnItemClickListener(this);
        recyclerView.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder v) {
        Toast.makeText(getApplicationContext(), "onItemClick\n" + v.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(RecyclerView.ViewHolder v) {
        Toast.makeText(getApplicationContext(), "onItemLongClick\n" + v.toString(), Toast.LENGTH_SHORT).show();
        return false;
    }
}
