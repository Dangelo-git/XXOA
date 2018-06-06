package com.dangelo.xxoa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.bean.meetinfo;

import java.util.List;


public class MeetingListAdapter extends BaseAdapter {

	private Context mContext;
	private static final String TAG = "ScheduleListAdapter";
	// private String cname = null;
	public int mposition;
	public static List<meetinfo> contentsList = null;
	private boolean SCflag =false;
	public MeetingListAdapter(Context context,

							  List<meetinfo> InfoList) {
		mContext = context;
		this.contentsList = InfoList;
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
			convertView = layoutInflater.inflate(R.layout.meetlist_item, null);
			viewvolderSub.name = (TextView)convertView.findViewById(R.id.framelist_sub_item_name);
			viewvolderSub.dot = (ImageView)convertView.findViewById(R.id.list_dot);

//			convertView.setTag(R.id.act_detail_actTitle,viewHolder);
			convertView.setTag(viewvolderSub);
		}else{
//			viewHolder = (ViewHolder)convertView.getTag(R.id.act_detail_actTitle);
			viewvolderSub = (ViewHolderSub)convertView.getTag();
		}

		viewvolderSub.name.setText(contentsList.get(position).getMeetName());
		if(contentsList.get(position).getStatus().equals("1")){
			viewvolderSub.dot.setBackground(mContext.getResources().getDrawable(R.drawable.deliver_red_icon));
		}

//		convertView.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
////				ShortCut.monArtcles = contentsList.get(position);
////				Intent mIntent = new Intent();
////				mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////				mIntent.setClass(mContext, ScheduleDetailActivity.class);
////				mContext.startActivity(mIntent);
//			}
//		});
		
		return convertView;
	}

	public class ViewHolderSub{
		public TextView name;
		public ImageView dot;
	}
}
