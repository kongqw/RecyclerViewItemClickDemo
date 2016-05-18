#自定义高效支持点击监听的RecyclerView
##效果图
![这里写图片描述](http://img.blog.csdn.net/20160518170111264)
##Demo

博客：[CSDN](http://blog.csdn.net/q4878802/article/details/51445526)


##传统做法

> 在Adapter内部直接对View添加点击事件

![P](http://img.blog.csdn.net/20160518170129911)

> 因为**这种方式虽然也可以解决点击监听问题**，但是效率不高，所以我就截了一张图，大家也就不要想着复制了，
> 
> 话说我原来也是一直用这种方式，直到看到一篇文章：[RecyclerView无法添加onItemClickListener最佳的高效解决方案](http://blog.csdn.net/liaoinstan/article/details/51200600)
> 
> 性能上肯定是有一定提升，下面我对此进行了一下封装


##封装

```
package kong.qingwei.recyclerviewitemclickdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by kqw on 2016/5/18.
 * KqwRecyclerView
 * 支持点击监听的RecyclerView
 */
public class KqwRecyclerView extends RecyclerView implements RecyclerView.OnItemTouchListener {

    private GestureDetectorCompat mGestureDetector;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public KqwRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initRecyclerView();
    }

    private void initRecyclerView(){
        mGestureDetector = new GestureDetectorCompat(getContext(), new ItemTouchHelperGestureListener());
        addOnItemTouchListener(this);
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder vh = getChildViewHolder(child);
                // 回调
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(vh);
                }
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View child = findChildViewUnder(e.getX(), e.getY());
            if (child!=null) {
                RecyclerView.ViewHolder vh = getChildViewHolder(child);
                // 回调
                if (mOnItemLongClickListener != null) {
                    mOnItemLongClickListener.onItemLongClick(vh);
                }
            }
        }
    }


    public interface OnItemClickListener {
        void onItemClick(ViewHolder v);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(ViewHolder v);
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        if (!isLongClickable()) {
            setLongClickable(true);
        }
        mOnItemLongClickListener = listener;
    }
}
```


##使用
> 使用和ListView一样

```
KqwRecyclerView recyclerView = (KqwRecyclerView) findViewById(R.id.recyclerView);
……
recyclerView.setOnItemClickListener(new KqwRecyclerView.OnItemClickListener() {
    @Override
    public void onItemClick(RecyclerView.ViewHolder v) {
        Toast.makeText(getApplicationContext(), "onItemClick\n" + v.toString(), Toast.LENGTH_SHORT).show();
    }
});
recyclerView.setOnItemLongClickListener(new KqwRecyclerView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(RecyclerView.ViewHolder v) {
        Toast.makeText(getApplicationContext(), "onItemLongClick\n" + v.toString(), Toast.LENGTH_SHORT).show();
        return false;
    }
});
```

