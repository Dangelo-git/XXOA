package com.dangelo.xxoa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dangelo.xxoa.R;
import com.dangelo.xxoa.bean.Topics;

import java.util.List;

/**
 * Created by mengguangao on 2016/6/8.
 */
public class TopicListAdapter extends BaseAdapter {
    private static final String TAG = TopicListAdapter.class.getName();
    private Context mContext;
    private List<Topics> mlist;
    public TopicListAdapter(Context context, List<Topics> list) {
        this.mContext = context;
        this.mlist = list;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.topiclist_item, null);
            viewHolder.title= (TextView) convertView.findViewById(R.id.title_topic_content_et);
            viewHolder.dept = (TextView) convertView.findViewById(R.id.addmeet_topic_dept_sp);
            viewHolder.seet = (TextView) convertView.findViewById(R.id.addmeet_topic_seet_sp);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.title.setText(mlist.get(position).getTopicName());
        viewHolder.dept.setText(mlist.get(position).getReportDept());
        viewHolder.seet.setText(mlist.get(position).getAttendDept());

        return convertView;
    }
    public final class ViewHolder {
        TextView title;
        TextView seet;
        TextView dept;
    }

}
