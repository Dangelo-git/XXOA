package com.dangelo.xxoa.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.bean.FileInfo;


import java.util.List;

/**
 * Created by mengguangao on 2016/6/8.
 */
public class DocListAdapter extends BaseAdapter {
    private static final String TAG = DocListAdapter.class.getName();
   // private static Map<Integer, Boolean> isSelected;
    private Context mContext;
    private List<FileInfo> mlist;
    private String packageName;
    private int clickTemp=0;//选中条目
    private int lastClick=-1;//上一次选中条目


    public DocListAdapter(Context context, List<FileInfo> list) {
//        if (isSelected == null) {
//            isSelected = new HashMap<Integer, Boolean>();
//        }
        this.mContext = context;
        this.mlist = list;
       // initData();
    }

    public void setClickTemp(int p) {

        this.clickTemp = p;

    }


    public int getClickTemp() {
        return clickTemp;
    }

    @Override
    public int getCount() {
        return mlist.size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.doclist_item, null);
            viewHolder.ctv = (TextView) convertView.findViewById(R.id.framelist_sub_item_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        packageName = mlist.get(position).getFileName();
        Log.i(TAG, packageName);

        viewHolder.ctv.setText(packageName);


        return convertView;
    }

    class ViewHolder {
        TextView ctv;
    }
}
