package com.dangelo.xxoa;


import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dangelo.xxoa.adapter.MulitselectListAdapter;
import com.dangelo.xxoa.bean.DeptInfo;
import com.dangelo.xxoa.uitl.ShortCut;

public class MultiselectActivity extends Activity {

	public TextView confirm, cancel;
	protected int activityCloseEnterAnimation;
	protected int activityCloseExitAnimation;
	public static final String TAG = MultiselectActivity.class.getSimpleName();
	public static final int REFRESH = 0;
	private boolean selectallflag = false;
	private SelectHandler mSelectHandler;
	private ListView keyListView;
	private LinearLayout allselectlayout;
	private ImageView allselect;
//	private Button confirmBtn;
	private MulitselectListAdapter mAdapter;
	private String viewId;


//	private List<String> deptList_list = new ArrayList<>();
public class SelectHandler extends Handler {
	// 子类必须重写此方法,接受数据
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		switch (msg.what) {
			case REFRESH:
				selectallflag = GetListSelcet();
				if (selectallflag) {
					allselect
							.setBackgroundResource(R.drawable.shop_car_select_bg);
				} else {
					allselect
							.setBackgroundResource(R.drawable.shop_car_noselect_bg);
				}
				break;
		}

	}
}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multiselect_activity);
		Bundle extras = getIntent().getExtras();
		viewId=extras.getString("viewId");//str即为回传的值
		if(viewId!=null) {
			Log.i(TAG, "viewId:" + viewId);
		}
		TypedArray activityStyle = getTheme().obtainStyledAttributes(
				new int[] { android.R.attr.windowAnimationStyle });
		int windowAnimationStyleResId = activityStyle.getResourceId(0, 0);
		activityStyle.recycle();
		activityStyle = getTheme().obtainStyledAttributes(
				windowAnimationStyleResId,
				new int[] { android.R.attr.activityCloseEnterAnimation,
						android.R.attr.activityCloseExitAnimation });

		// activityStyle = getTheme().obtainStyledAttributes(
		// windowAnimationStyleResId,
		// new int[] { R.anim.slide_in_bottom,
		// R.anim.slide_out_bottom });
		activityCloseEnterAnimation = activityStyle.getResourceId(0, 0);
		activityCloseExitAnimation = activityStyle.getResourceId(1, 0);
		Log.v(TAG, activityCloseEnterAnimation + "");
		Log.v(TAG, activityCloseExitAnimation + "");
		activityStyle.recycle();
		mSelectHandler = new SelectHandler();
		ShortCut.SelectHandler = mSelectHandler;


		keyListView = (ListView) findViewById(R.id.multiselect_listview);



//		confirmBtn = (Button)
//				findViewById(R.id.multiselect_activity_buyBtn);
		confirm = (TextView) findViewById(R.id.multiselect_activity_confirmBtn);
		cancel = (TextView) findViewById(R.id.multiselect_activity_cancelBtn);
		allselectlayout = (LinearLayout)
				findViewById(R.id.multiselect_allselect_layout);
		allselect = (ImageView)
				findViewById(R.id.multiselect_allselect_icon);
		allselectlayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!selectallflag) {
					allselect
							.setBackgroundResource(R.drawable.shop_car_select_bg);
				} else {
					allselect
							.setBackgroundResource(R.drawable.shop_car_noselect_bg);
				}
				SetListSelcet(selectallflag);
				selectallflag = !selectallflag;
			}
		});
		confirm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub


						ShortCut.ConfirmdeptList_list.clear();
						for(int i=0;i<ShortCut.deptList_list.size();i++){
							if(ShortCut.deptList_list.get(i).isSelect()){
								DeptInfo mproduct = ShortCut.deptList_list.get(i);
								ShortCut.ConfirmdeptList_list.add(mproduct);

							}
						}
						mAdapter.notifyDataSetChanged();




				if (ShortCut.ConfirmdeptList_list.size() >0) {
					Bundle bundle = new Bundle();
					bundle.putString("viewId", viewId);
					setResult(RESULT_OK, MultiselectActivity.this.getIntent().putExtras(bundle));
					finish();
				} else {
					Toast.makeText(MultiselectActivity.this, "请选择参会人员！",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		initContent();
	}

	protected void initContent() {
		// TODO Auto-generated method stub


		loadDate();

		buildScheduleList();
		// new Handler().postDelayed(new Runnable() {
		// public void run() {
		// if(sizeitem!=-1){
		// sizegridView.getChildAt(sizeitem).setBackgroundColor(getResources().getColor(R.color.green));
		// }
		// if(coloritem!=-1){
		// colorgridView.getChildAt(coloritem).setBackgroundColor(getResources().getColor(R.color.green));
		// }
		// }
		// },400);
		Message message = new Message();
		message.what = MultiselectActivity.REFRESH;
		mSelectHandler.handleMessage(message);
	}
	// 根据数值获取是否需要显示全选
	private boolean GetListSelcet() {
		boolean flag = true;
		if (ShortCut.deptList_list != null && ShortCut.deptList_list.size() != 0) {
			for (int i = 0; i < ShortCut.deptList_list.size(); i++) {
				if (!ShortCut.deptList_list.get(i).isSelect()) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	// 设置全选和取消全选时处理数据
	private void SetListSelcet(boolean selectallflag) {
		if (ShortCut.deptList_list != null && ShortCut.deptList_list.size() != 0) {
			if (!selectallflag) {
				for (int i = 0; i < ShortCut.deptList_list.size(); i++) {
					ShortCut.deptList_list.get(i).setSelect(true);
				}
			} else {
				allselect
						.setBackgroundResource(R.drawable.shop_car_noselect_bg);
				for (int i = 0; i < ShortCut.deptList_list.size(); i++) {
					ShortCut.deptList_list.get(i).setSelect(false);
				}
			}
			mAdapter.notifyDataSetChanged();
		}

	}
	private void loadDate() {
		ShortCut.deptList_list.clear();
		if(ShortCut.MeetBasis!=null){
			for(DeptInfo Dept :ShortCut.MeetBasis.getDeptList()){
				ShortCut.deptList_list.add(Dept);
			}

		}

	}

	private void buildScheduleList() {
		// TODO Auto-generated method stub

		mAdapter = new MulitselectListAdapter(this, ShortCut.deptList_list);
		keyListView.setAdapter(mAdapter);
		setListViewHeightBasedOnChildren(keyListView);
		keyListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);



	}
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.e("MainKeyDown", "" + keyCode);
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			finish();
		}
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		// setValue();
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void finish() {
		Log.i(TAG, "finish");
		// MyOrderListServiceActivity.this.overridePendingTransition(R.anim.slide_in_bottom,
		// R.anim.slide_out_bottom);
		// this.overridePendingTransition(R.anim.slide_out_bottom,0);
		// TODO Auto-generated method stub

		super.finish();
		overridePendingTransition(activityCloseEnterAnimation,
				activityCloseExitAnimation);

	}


}
