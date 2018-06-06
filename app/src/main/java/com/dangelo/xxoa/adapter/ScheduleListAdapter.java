package com.dangelo.xxoa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.dangelo.xxoa.R;
import com.dangelo.xxoa.bean.schedule;

import java.util.List;


public class ScheduleListAdapter extends BaseAdapter {

	private Context mContext;
	private static final String TAG = "ScheduleListAdapter";
	// private String cname = null;
	public int mposition;
	public static List<schedule> contentsList = null;
	private boolean SCflag =false;
	public ScheduleListAdapter(Context context,

							   List<schedule> ContentsList) {
		mContext = context;
		this.contentsList = ContentsList;
	}

	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return contentsList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return contentsList.get(position);
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

		if(convertView == null){
			viewvolderSub = new ViewHolderSub();
			convertView = layoutInflater.inflate(R.layout.calederlist_item, null);
			viewvolderSub.name = (TextView)convertView.findViewById(R.id.framelist_sub_item_name);

//			convertView.setTag(R.id.act_detail_actTitle,viewHolder);
			convertView.setTag(viewvolderSub);
		}else{
//			viewHolder = (ViewHolder)convertView.getTag(R.id.act_detail_actTitle);
			viewvolderSub = (ViewHolderSub)convertView.getTag();
		}

		viewvolderSub.name.setText(contentsList.get(position).getTopic());

		
		return convertView;
	}
//
	protected void showToast(String text){
		Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
	}
	public class ViewHolderSub{
		public TextView name;
//		public TextView date;
//		public TextView time;
//		public ImageView img;
//		public LinearLayout layout;
	}
}
