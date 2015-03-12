# SwipeRefreshListView
SwipeRefreshLayout combine ListView secondary encapsulation.

SwipeRefreshListView mListView = (SwipeRefreshListView) findViewById(R.id.listview);
		mListView.setAdapter(mAdapter);
		mListView.setOnRefreshListener(this);
		mListView.setItemClickListener(this);
		mListView.setLoadMoreListener(this);
