package com.dangelo.xxoa.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;


import com.blankj.utilcode.utils.Utils;
import com.dangelo.xxoa.dao.DaoMaster;
import com.dangelo.xxoa.dao.DaoSession;
import com.dangelo.xxoa.receiver.MJpushReceiver;
import com.dangelo.xxoa.tools.Jpush;
import com.dangelo.xxoa.uitl.FileService;
import com.dangelo.xxoa.uitl.JlogUtils;
import com.dangelo.xxoa.uitl.WakeLockUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;



public class XZOAApplication extends Application implements MJpushReceiver.PushCallback {
	public static XZOAApplication mInstance = null;
	private List<Activity> mList = new LinkedList<Activity>();
	private Set<String> tags=new HashSet<String>();
	private static DaoMaster daoMaster;
	private static DaoSession daoSession;
	private MJpushReceiver mJpushReceiver = new MJpushReceiver();
//	private static Logger sLogger;
	@Override
	public void onCreate() {
		super.onCreate();
//		mInstance = this;
//		 JPushInterface.setDebugMode(true);
//         JPushInterface.init(this);
		initImageLoader(getApplicationContext());
		Utils.init(getApplicationContext());
		JlogUtils.initJlog();
		Jpush.initJpush();
		mJpushReceiver.SetOnBind(this);

	}

	public static XZOAApplication getInstance() {
		if (null == mInstance) {
			mInstance = new XZOAApplication();
		}
		return mInstance;
	}
	public static void initImageLoader(Context context) {
		File cacheDir = new File(FileService.getCachePath(context));
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
//				.discCache(new UnlimitedDiscCache(cacheDir))
//				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		ImageLoader.getInstance().init(config);
	}
	public void addActivity(Activity activity) {
		mList.add(activity);
	}
	public void exit() {
		try {
			for (Activity activity : mList) {
				if (activity != null)
					activity.finish();
				Log.d("exitactivity", activity.toString());
			}
			WakeLockUtil.releaseWakeLock();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
//	private void initJpush() {
//		JPushInterface.setDebugMode(true);
//		JPushInterface.init(this);
//		boolean isCon= JPushInterface.getConnectionState(this);
//		Log.i("isCon", isCon + "");
////        注册registration_id=170976fa8a8220f42d4
//		//Log.i("注册registration_id", JPushInterface.getRegistrationID(this));
//		tags.add("htcone");
//
//		JPushInterface.setAlias(this, "huwei", new TagAliasCallback() {
//			@Override
//			public void gotResult(int i, String s, Set<String> set) {
//				Log.i("setAlias", "huwei" + s + "");
//			}
//		});
//
////        设置通知别名alias与标签tags
//
//		JPushInterface.setTags(this, tags, new TagAliasCallback() {
//			@Override
//			public void gotResult(int i, String s, Set<String> set) {
//				Log.i("resp", String.valueOf(i));
//				if (i == 0) {
//					Log.i("tags", "设置tags成功");
//				}
//			}
//		});
//	}
	/**
	 * 取得DaoMaster
	 *
	 * @param context        上下文
	 * @return               DaoMaster
	 */
	public static DaoMaster getDaoMaster(Context context) {
		if (daoMaster == null) {
			DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,"MESSAGE_INFO",null);
			daoMaster = new DaoMaster(helper.getWritableDatabase());
		}
		return daoMaster;
	}

	/**
	 * 取得DaoSession
	 *
	 * @param context        上下文
	 * @return               DaoSession
	 */
	public static DaoSession getDaoSession(Context context) {
		if (daoSession == null) {
			if (daoMaster == null) {
				daoMaster = getDaoMaster(context);
			}
			daoSession = daoMaster.newSession();
		}
		return daoSession;
	}

	@Override
	public void PushReceiveResult(String pushInfo, String push_code) {

	}

	@Override
	public void PushOpenDResult(String pushInfo, String push_code) {

	}

	@Override
	public void PushMessageResult(String pushInfo, String push_code) {

	}
}
