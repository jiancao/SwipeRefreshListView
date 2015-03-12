package com.jiancao.swiperefresh;

import com.example.swiperefreshlistview.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * 
 * @author caojian
 * @description SwipeRefreshLayout ListView封装
 * @date 2015-1-19下午3:00:16
 */
public class SwipeRefreshListView extends SwipeRefreshLayout implements
OnScrollListener, OnItemClickListener {

	private ListView listView;
	private LinearLayout loadMoreLayout;// FooterView布局
	private LoadMoreListener loadMoreListener;
	private ItemClickListener itemClickListener;

	public SwipeRefreshListView(Context context) {
		super(context);
		listView = new ListView(context);
		initView(context);
	}

	public SwipeRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		listView = new ListView(context, attrs);
		initView(context);
	}

	/**
	 * 
	 * @description 设置加载更多监听
	 * @author CaoJian
	 * @date 2015-1-19下午4:14:35
	 * @param loadMoreListener
	 */
	public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
		this.loadMoreListener = loadMoreListener;
	}

	/**
	 * 
	 * @description 设置点击监听
	 * @author CaoJian
	 * @date 2015-1-19下午4:14:47
	 * @param itemClickListener
	 */
	public void setItemClickListener(ItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}

	/**
	 * 
	 * @description 初始化界�?
	 * @author CaoJian
	 * @date 2015-1-19下午3:11:58
	 * @param context
	 */
	private void initView(Context context) {
		listView.setId(android.R.id.list);//给这个listview添加�?个默认的id
		listView.setDivider(null);
		listView.setDividerHeight(0);
		listView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		listView.setOnScrollListener(this);
		listView.setOnItemClickListener(this);
		this.addView(listView);

		// 加载更多
		loadMoreLayout = (LinearLayout) LayoutInflater.from(context).inflate(
				R.layout.layout_load_more, null);
		listView.addFooterView(loadMoreLayout);
		loadMoreLayout.setVisibility(View.GONE);

		// 设置刷新颜色
		setColorSchemeResources(R.color.refresh_color1, R.color.refresh_color2,
				R.color.refresh_color3, R.color.refresh_color4);

	}

	/**
	 * 
	 * @description 设置适配�?
	 * @author CaoJian
	 * @date 2015-1-19下午3:12:16
	 * @param adapter
	 */
	public void setAdapter(BaseAdapter adapter) {
		listView.setAdapter(adapter);
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		if (!refreshing && null != loadMoreLayout) {
			loadMoreLayout.setVisibility(View.GONE);
		}
		super.setRefreshing(refreshing);
	}

	/**
	 * 
	 * @description 是否显示加载更多
	 * @author CaoJian
	 * @date 2015-1-19下午3:14:40
	 * @param visible
	 */
	public void setFooterViewVisible(int visible) {
		loadMoreLayout.setVisibility(visible);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// 滚动至底�?
		if (firstVisibleItem!=0 && view.getLastVisiblePosition() == view.getCount() - 1) {
			loadMoreLayout.setVisibility(View.VISIBLE);
			loadMoreListener.loadMore();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if(itemClickListener!=null){
			itemClickListener.itemClick(position);
		}
	}

	public interface LoadMoreListener {

		public void loadMore();
	}

	public interface ItemClickListener {

		public void itemClick(int position);
	}
}
