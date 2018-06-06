package com.dangelo.xxoa.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.bean.dispatchDoc;
import com.dangelo.xxoa.bean.inDoc;
import com.dangelo.xxoa.ui.SwipeMenuLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * @author mga.
 * @date on 2017/10/20 14:14.
 * 活动界面适配器
 */

public class InDocRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<inDoc> list;
    public static final int TYPE_COMMON_VIEW = 100001;//普通类型 Item
    public static final int TYPE_FOOTER_VIEW = 100002;//footer类型 Item
    public static final int TYPE_HEADER_VIEW = 100006;//head类型 Item
    public static final int TYPE_EMPTY_VIEW = 100003;//empty view，即初始化加载时的提示View
    public static final int TYPE_NODATE_VIEW = 100004;//初次加载无数据的默认空白view
    public static final int TYPE_RELOAD_VIEW = 100005;//初次加载无数据的可重新加载或提示用户的view

    protected Context mContext;
    private boolean mOpenLoadMore;//是否开启加载更多
    private boolean mOpenHead;//是否开启列表头
    private boolean isAutoLoadMore = true;//是否自动加载，当数据不满一屏幕会自动加载

    private boolean isRemoveEmptyView;

    private View mLoadingView; //分页加载中view
    private View mLoadFailedView; //分页加载失败view
    private View mLoadEndView; //分页加载结束view
    private View mEmptyView; //首次预加载view
    private View mReloadView; //首次预加载失败、或无数据的view
    private RelativeLayout mFooterLayout;//footer view

    private boolean isReset;//开始重新加载数据
    private final String TAG = InDocRecyclerAdapter.class.getSimpleName();
    private MyItemClickListener mItemclickListener;
    private MyItemDeleteClickListener mDeleteListener;

    private boolean isSwipe = false;

    public interface OnLoadMoreListener {
        /**
         * 加载更多的回调方法
         *
         * @param isReload 是否是重新加载，只有加载失败后，点击重新加载时为true
         */
        void onLoadMore(boolean isReload);
    }

    private OnLoadMoreListener mLoadMoreListener;

    public InDocRecyclerAdapter(Context context, List<inDoc> datas, boolean isOpenLoadMore, boolean ismOpenHead) {
        mContext = context;
        list = datas == null ? new ArrayList<inDoc>() : datas;
        Log.i(TAG, "list length : " + list.size());
        mOpenLoadMore = isOpenLoadMore;
        mOpenHead = ismOpenHead;
    }
    public void resetRecordDatas(List<inDoc> datas) {
        Log.i(TAG, "resetRecordDatas : " + datas.size());
        this.list.clear();
        list.addAll(datas);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        switch (viewType) {
            case TYPE_FOOTER_VIEW:
                if (mFooterLayout == null) {
                    mFooterLayout = new RelativeLayout(mContext);
                }
                viewHolder = MyViewHolder.create(mFooterLayout);
                break;
            case TYPE_EMPTY_VIEW:
                viewHolder = MyViewHolder.create(mEmptyView);
                break;
            case TYPE_NODATE_VIEW:
                viewHolder = MyViewHolder.create(new View(mContext));
                break;
            case TYPE_RELOAD_VIEW:
                viewHolder = MyViewHolder.create(mReloadView);
                break;
            case TYPE_COMMON_VIEW:
                viewHolder = new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.dispdoc_list_item, parent, false));
                break;
            case TYPE_HEADER_VIEW:
                viewHolder = new HeadViewHolder(LayoutInflater.from(mContext).inflate(R.layout.sysmsg_list_head, parent, false));
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        if (holder instanceof ItemViewHolder) {

            inDoc adList = list.get(position-getheadViewCount());

            if (adList == null) {
                return;
            }
            Log.i(TAG, "活动条目内容 : {" + adList.toString() + "}");

            final ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.adRootRl.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemclickListener.onItemClick(viewHolder.adRootRl,position-getheadViewCount());
                }
            });
            viewHolder.adTv.setText(adList.getFileTitle());
//            viewHolder.adTvdate.setText(TimeManagement.longToStringDate(adList.getDate().getTime(),"yyyy-MM-dd HH:mm:ss"));
//            ((ItemViewHolder) holder).btnDelete.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (mDeleteListener != null) {
//                        //((CstSwipeDelMenu) holder.itemView).quickClose();
//                        mDeleteListener.onItemClick(view, holder, position-getheadViewCount());
//                    }
//                }
//            });
            ((SwipeMenuLayout) holder.itemView).setSwipeEnable(isSwipe);
        }
    }

    @Override
    public int getItemCount() {
        if (list.isEmpty() && mEmptyView != null) {
            return 1+getheadViewCount();
        }
//        JLog.i(TAG,"list.size():"+list.size()+" getFooterViewCount()"+ getFooterViewCount()+" getheadViewCount()"+getheadViewCount());
        return list.size() + getFooterViewCount()+getheadViewCount();
    }

    @Override
    public int getItemViewType(int position) {
         if (isHeadView(position)) {
            return TYPE_HEADER_VIEW;
        }
        if (list.isEmpty()) {
            if (mEmptyView != null && !isRemoveEmptyView) {
                return TYPE_EMPTY_VIEW;
            }

            if (isRemoveEmptyView && mReloadView != null) {
                return TYPE_RELOAD_VIEW;
            } else {
                return TYPE_NODATE_VIEW;
            }
        }

        if (isFooterView(position)) {
            return TYPE_FOOTER_VIEW;
        }

        return TYPE_COMMON_VIEW;
    }

    /**
     * 根据positiond得到data
     *
     * @param position
     * @return
     */
    public inDoc getItem(int position) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(position-getheadViewCount());
    }

    /**
     * 是否是FooterView
     *
     * @param position
     * @return
     */
    private boolean isFooterView(int position) {
        return mOpenLoadMore && position >= (getItemCount() - 1 - getheadViewCount());
    }
    /**
     * 是否是FooterView
     *
     * @param position
     * @return
     */
    private boolean isHeadView(int position) {
        return  mOpenHead&&position == 0;
    }

    /**
     * StaggeredGridLayoutManager模式时，FooterView可占据一行
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isFooterView(holder.getLayoutPosition())) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    /**
     * GridLayoutManager模式时， FooterView可占据一行，判断RecyclerView是否到达底部
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isFooterView(position)) {
                        return gridManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
        startLoadMore(recyclerView, layoutManager);
    }


    /**
     * 判断列表是否滑动到底部
     *
     * @param recyclerView
     * @param layoutManager
     */
    private void startLoadMore(RecyclerView recyclerView, final RecyclerView.LayoutManager layoutManager) {
        if (!mOpenLoadMore || mLoadMoreListener == null) {
            return;
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!isAutoLoadMore && findLastVisibleItemPosition(layoutManager) + 1 == getItemCount()) {
                        scrollLoadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isAutoLoadMore && findLastVisibleItemPosition(layoutManager) + 1 == getItemCount()) {
                    scrollLoadMore();
                } else if (isAutoLoadMore) {
                    isAutoLoadMore = false;
                }
            }
        });
    }

    /**
     * 到达底部开始刷新
     */
    private void scrollLoadMore() {
        if (isReset) {
            return;
        }
        if (mFooterLayout.getChildAt(0) == mLoadingView) {
            if (mLoadMoreListener != null) {
                mLoadMoreListener.onLoadMore(false);
            }
        }
    }

    private int findLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
            return findMax(lastVisibleItemPositions);
        }
        return -1;
    }

    /**
     * 清空footer view
     */
    public void removeFooterView() {
        mFooterLayout.removeAllViews();
    }

    /**
     * 添加新的footer view
     *
     * @param footerView
     */
    private void addFooterView(View footerView) {
        if (footerView == null) {
            return;
        }

        if (mFooterLayout == null) {
            mFooterLayout = new RelativeLayout(mContext);
        }
        removeFooterView();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mFooterLayout.addView(footerView, params);
    }

    /**
     * 刷新加载更多的数据
     *
     * @param datas
     */
    public void setLoadMoreData(List<inDoc> datas) {
        int size = datas.size();
        list.addAll(datas);
        notifyItemInserted(size);
    }

    /**
     * 下拉刷新，得到的新数据查到原数据起始
     *
     * @param datas
     */
    public void setData(List<inDoc> datas) {
        list.addAll(0, datas);
        notifyDataSetChanged();
    }

    /**
     * 初次加载、或下拉刷新要替换全部旧数据时刷新数据
     *
     * @param datas
     */
    public void setNewData(List<inDoc> datas) {
        if (isReset) {
            isReset = false;
        }
        list.clear();
        list.addAll(datas);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 初始化加载中布局
     *
     * @param loadingView
     */
    public void setLoadingView(View loadingView) {
        mLoadingView = loadingView;
        addFooterView(mLoadingView);
    }

    public void setLoadingView(int loadingId) {
        setLoadingView(inflate(mContext, loadingId));
    }

    /**
     * 初始加载失败布局
     *
     * @param loadFailedView
     */
    public void setLoadFailedView(View loadFailedView) {
        mLoadFailedView = loadFailedView;
    }

    public void setLoadFailedView(int loadFailedId) {
        setLoadFailedView(inflate(mContext, loadFailedId));
    }

    /**
     * 初始化全部加载完成布局
     *
     * @param loadEndView
     */
    public void setLoadEndView(View loadEndView) {
        mLoadEndView = loadEndView;
    }

    public void setLoadEndView(int loadEndId) {
        setLoadEndView(inflate(mContext, loadEndId));
    }

    /**
     * 初始化emptyView
     *
     * @param emptyView
     */
    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    /**
     * 移除emptyView
     */
    public void removeEmptyView() {
        isRemoveEmptyView = true;
        notifyDataSetChanged();
    }

    /**
     * 初次预加载失败、或无数据可显示该view，进行重新加载或提示用户无数据
     *
     * @param reloadView
     */
    public void setReloadView(View reloadView) {
        mReloadView = reloadView;
        isRemoveEmptyView = true;
        notifyDataSetChanged();
    }

    /**
     * 返回 footer view数量
     *
     * @return
     */
    public int getFooterViewCount() {
        return mOpenLoadMore && !list.isEmpty() ? 1 : 0;
    }
    /**
     * 返回 footer view数量
     *
     * @return
     */
    public int getheadViewCount() {
        return mOpenHead  ? 1 : 0 ;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }

    /**
     * 重置adapter，恢复到初始状态
     */
    public void reset() {
        if (mLoadingView != null) {
            addFooterView(mLoadingView);
        }
        isReset = true;
        isAutoLoadMore = true;
        list.clear();
    }

    /**
     * 数据加载完成
     */
    public void loadEnd() {
        if (mLoadEndView != null) {
            addFooterView(mLoadEndView);
        }
    }

    /**
     * 数据加载失败
     */
    public void loadFailed() {
        addFooterView(mLoadFailedView);
        mLoadFailedView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addFooterView(mLoadingView);
                if (mLoadMoreListener != null) {
                    mLoadMoreListener.onLoadMore(true);
                }
            }
        });
    }

    public MyItemClickListener mListener;
    public  class ItemViewHolder extends ViewHolder {


        private TextView adTv;
        private TextView adTvdate;
        private ImageView adIv;
        private MyItemClickListener mListener;
        private RelativeLayout adRootRl;
        Button btnDelete;


        public ItemViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            adRootRl=(RelativeLayout)itemView.findViewById(R.id.dispdoc_item_root);
            adTv = (TextView) itemView.findViewById(R.id.dispdoc_item_title_tx);
//            adTvdate = (TextView) itemView.findViewById(R.id.dispdoc_item_title_date);
            adIv = (ImageView) itemView.findViewById(R.id.dispdoc_item_image_iv);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);


        }


    }
    public  class HeadViewHolder extends ViewHolder {


        private TextView msghradTv;
        private RelativeLayout msgheadRootRl;


        public HeadViewHolder(View headView) {
            super(headView);
            initView(headView);
        }

        private void initView(View headView) {
            msgheadRootRl=(RelativeLayout)headView.findViewById(R.id.sysmsg_news_layout);
            msghradTv = (TextView) headView.findViewById(R.id.sysmsg_news_tx);
            msgheadRootRl.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });


        }


    }

    private static class MyViewHolder extends ViewHolder {
        /**
         * 私有构造方法
         *
         * @param itemView
         */
        private MyViewHolder(View itemView) {
            super(itemView);
        }

        public static MyViewHolder create(View itemView) {
            return new MyViewHolder(itemView);
        }
    }

    /**
     * StaggeredGridLayoutManager时，查找position最大的列
     *
     * @param lastVisiblePositions
     * @return
     */
    public static int findMax(int[] lastVisiblePositions) {
        int max = lastVisiblePositions[0];
        for (int value : lastVisiblePositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static View inflate(Context context, int layoutId) {
        if (layoutId <= 0) {
            return null;
        }
        return LayoutInflater.from(context).inflate(layoutId, null);
    }



    public interface MyItemClickListener {
        void onItemClick(View view, int postion);
    }

    public void setOnItemClickListener(MyItemClickListener itemClickListener) {
        this.mItemclickListener = itemClickListener;
    }
    public void setSwipeEnable(boolean enable) {
        isSwipe = enable;
    }


    public interface MyItemDeleteClickListener {
        void onItemClick(View view, ViewHolder holder, int postion);
    }

    public void setOnItemDeleteClickListener(MyItemDeleteClickListener itemClickListener) {
        this.mDeleteListener = itemClickListener;
    }

}
