package com.dangelo.xxoa.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.bean.Broese;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mengguangao on 2016/6/8.
 */
public class BroeseListAdapter extends BaseAdapter {
    private static final String TAG = BroeseListAdapter.class.getName();
   // private static Map<Integer, Boolean> isSelected;
    private Context mContext;
    private List<Broese> mlist;
    private String packageName;
    private String content;
    private int clickTemp=0;//选中条目
    private int lastClick=-1;//上一次选中条目
    private int mbusinessId;


    public BroeseListAdapter(Context context) {
        this.mContext = context;
    }

    public void resetDatas(List<Broese> recordDatas) {
        this.mlist = (ArrayList) recordDatas;
        notifyDataSetChanged();
    }

    public void setClickTemp(int p) {

        this.clickTemp = p;

    }

//    private void initData() {
//
//        for (int i = 0; i < mlist.size(); i++) {
//
//            getIsSelected().put(i, false);
//
//        }
//    }

    //    public static void setIsSelected(Map<Integer, Boolean> isSelected) {
//        PackageAdapter.isSelected = isSelected;
//    }
//    public static Map<Integer, Boolean> getIsSelected() {
//        return isSelected;
//    }

    public int getClickTemp() {
        return clickTemp;
    }

    @Override
    public int getCount() {
        return (mlist == null || mlist.isEmpty()) ? 0 : mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.broeselist_item, null);
            viewHolder.ctv = (TextView) convertView.findViewById(R.id.package_name_tv);
            viewHolder.content = (TextView) convertView.findViewById(R.id.package_content_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        packageName = mlist.get(position).getTitle();
        content = mlist.get(position).getContent();
        if(content==null||content.equals("")){
            viewHolder.content.setVisibility(View.GONE);
        }else{
            viewHolder.content.setVisibility(View.VISIBLE);
        }
        Log.i(TAG, packageName);
//        viewHolder.ctv.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v)
//            {
//               ((CheckedTextView) v).toggle();
//            }
//        });

        viewHolder.ctv.setText(packageName);
        viewHolder.content.setText(content.trim());

//      if(clickTemp==position){
//          viewHolder.ctv.setBackground(mContext.getResources().getDrawable(R.drawable.grid_item_checked));
//      }else{
//          viewHolder.ctv.setBackground(mContext.getResources().getDrawable(R.drawable.grid_item_unchecked));
//
//      }

//
//        if (isSelected.get(position)) {
//            Log.i("show", position + "//" + isSelected.get(position));
//            viewHolder.ctv.setBackground(mContext.getResources().getDrawable(R.drawable.grid_item_checked));
//
//        } else {
//            Log.i("show", position + "//" + isSelected.get(position));
//            viewHolder.ctv.setBackground(mContext.getResources().getDrawable(R.drawable.grid_item_unchecked));
//
//        }

        return convertView;
    }

    class ViewHolder {
        TextView ctv;
        TextView content;
    }
}
