package com.dangelo.xxoa.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.dangelo.xxoa.MultiselectActivity;
import com.dangelo.xxoa.R;
import com.dangelo.xxoa.bean.DeptInfo;
import com.dangelo.xxoa.uitl.ShortCut;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MulitselectListAdapter extends BaseAdapter {

	private Context mContext;
	private static final String TAG = "MulitselectListAdapter";
	// private String cname = null;
	public int mposition;
	public static List<DeptInfo> deptInfolist = new ArrayList<DeptInfo>();
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	public MulitselectListAdapter(Context context, List<DeptInfo> DeptInfolist) {
		mContext = context;
		this.deptInfolist = DeptInfolist;
		Log.i(TAG, "deptInfolist:" + deptInfolist.size());
	}

	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return deptInfolist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return deptInfolist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolderSub viewvolderSub = null;
		LayoutInflater layoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			viewvolderSub = new ViewHolderSub();
			convertView = layoutInflater.inflate(R.layout.mulitselectlist_item,
					null);
			viewvolderSub.name = (TextView) convertView
					.findViewById(R.id.mulitselectlist_item_name);
			// viewvolderSub.color =
			// (TextView)convertView.findViewById(R.id.shopcarlist_item_color);

			viewvolderSub.num = (TextView) convertView
					.findViewById(R.id.mulitselectlist_item_num);

			viewvolderSub.code = (TextView) convertView
					.findViewById(R.id.mulitselectlist_item_code);
			// viewvolderSub.size =
			// (TextView)convertView.findViewById(R.id.shopcarlist_item_size);

			viewvolderSub.selectIcon = (ImageView) convertView
					.findViewById(R.id.mulitselectlist_item_selcet_icon);
			viewvolderSub.layout = (RelativeLayout) convertView
					.findViewById(R.id.mulitselectlist_item_layout);

			convertView.setTag(viewvolderSub);
		} else {
			viewvolderSub = (ViewHolderSub) convertView.getTag();
		}
		viewvolderSub.name.setText(deptInfolist.get(position).getCName());

		viewvolderSub.num
				.setText(deptInfolist.get(position).getOrderCode());
//		viewvolderSub.code.setText(deptInfolist.get(position).getOrderCode());
		viewvolderSub.code.setVisibility(View.INVISIBLE);
		viewvolderSub.num.setVisibility(View.INVISIBLE);
		if (deptInfolist.get(position).isSelect()) {
			viewvolderSub.selectIcon
					.setBackgroundResource(R.drawable.shop_car_select_bg);
		} else {
			viewvolderSub.selectIcon
					.setBackgroundResource(R.drawable.shop_car_noselect_bg);
		}

		viewvolderSub.selectIcon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ShortCut.deptList_list.get(position).setSelect(
						!ShortCut.deptList_list.get(position).isSelect());
				notifyDataSetChanged();
				SendMessage();
			}
		});
		viewvolderSub.layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ShortCut.deptList_list.get(position).setSelect(
						!ShortCut.deptList_list.get(position).isSelect());
				notifyDataSetChanged();
				SendMessage();
			}
		});



//

		return convertView;
	}

	public class ViewHolderSub {
		public TextView name;

		public TextView num;
		public TextView code;
		public RelativeLayout layout;// selcectlayout

		public ImageView selectIcon;


	}

	private void SendMessage() {
		if (ShortCut.SelectHandler != null) {
			// Bundle bundle = new Bundle();
			// bundle.putString("bound", bound); // 往Bundle中存放数据
			// bundle.putString("categrey", categrey); // 往Bundle中put数据
			Message message = new Message();
			message.what = MultiselectActivity.REFRESH;
			// message.setData(bundle);
			ShortCut.SelectHandler.sendMessage(message);
			Log.i(TAG,
					"ShopCarActivity.REFRESH---》.ShortCut.SelectHandler.sendMessage(message)");
		}
	}

}
