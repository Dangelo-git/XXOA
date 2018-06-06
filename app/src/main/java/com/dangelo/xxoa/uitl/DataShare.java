package com.dangelo.xxoa.uitl;

import android.content.Context;
import android.content.SharedPreferences;


public class DataShare {

	Context mContext=null;
	private String tableString = "XZOA";
	
	public DataShare(Context context) {
		mContext =context;
	}
	
	 public String read(String key)
		{ 
			//String strName,strPassword; 
			SharedPreferences user = mContext.getSharedPreferences(tableString,0);
			/*
		     * SharedPreferences支持string,int,float,long等
		     * 模式为私有(Context.MODE_PRIVATE)值0，
		     * 公开可读(Context.MODE_WORLD_READABLE)值1，
		     * 公开可写（Context.MODE_WORLD_WRITEABLE）值2
		     * 或者某种组合
		     * 追加形式(Context.MODE_APPEND)
		     * 
		     * */
		    return user.getString(key,"");
			//strPassword = user.getString("PASSWORD",""); 
		} 
			 
		public void write(String strKey,String strKeyValue)
		{ 
				 SharedPreferences.Editor   user = mContext.getSharedPreferences(tableString,0).edit();
				 user.putString(strKey,strKeyValue); 
				 user.commit(); 
		}
		
		public void clear()
		{ 
//			Toast.makeText(mContext, "清楚金币数！还原！", Toast.LENGTH_LONG).show();
				 SharedPreferences.Editor   user = mContext.getSharedPreferences(tableString,0).edit();
				 user.clear(); 
				 user.commit(); 
		}
		
		public void remove(String strKey)
		{ 
				 SharedPreferences.Editor   user = mContext.getSharedPreferences(tableString,0).edit();
				 //user.remove(strKey); 
				 user.putString(strKey,""); 
				 user.commit(); 
		}                                                                                                                                                                                   
}