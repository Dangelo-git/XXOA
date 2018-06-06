package com.dangelo.xxoa.uitl;

import android.content.Context;


import com.dangelo.xxoa.application.XZOAApplication;
import com.dangelo.xxoa.dao.DaoSession;
import com.dangelo.xxoa.dao.FileInfo;
import com.dangelo.xxoa.dao.FileInfoDao;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;


/**
 * Created by Mengguangao on 2016/5/18.
 */
public class DbUtil {
    private static final String TAG = DbUtil.class.getSimpleName();
    private static DbUtil instance;
    private static Context appContext;
    private DaoSession mDaoSession;
    private FileInfoDao fileInfoDao;

    private DbUtil() {
    }

    /**
     * 采用单例模式
     *
     * @param context 上下文
     * @return dbservice
     */
    public static DbUtil getInstance(Context context) {
        if (instance == null) {
            instance = new DbUtil();
            if (appContext == null) {
                appContext = context.getApplicationContext();
            }
            instance.mDaoSession = XZOAApplication.getDaoSession(context);
            instance.fileInfoDao = instance.mDaoSession.getFileInfoDao();

        }
        return instance;
    }



    /**
     * 取出所有数据
     *
     * @return 所有数据信息
     */
    public List<FileInfo> loadAllNote() {
        return fileInfoDao.loadAll();
    }

    /**
     * 生成按id倒排序的列表
     *
     * @return 倒排数据
     */
    public List<FileInfo> loadAllNoteByOrder() {
        return fileInfoDao.queryBuilder().orderAsc(FileInfoDao.Properties.Id).list();
    }




    /**
     * 根据URL查询条件,返回数据列表
     *
     * @param params 参数
     * @return 数据列表
     */
    public List<FileInfo> queryNoteByURL(String params) {
        QueryBuilder qb = fileInfoDao.queryBuilder();

        qb.where(FileInfoDao.Properties.FileUrl.eq(params));


        return qb.list();

    }
    /**
     * 根据用户信息,插件或修改信息
     *
     * @param user 用户信息
     * @return 插件或修改的用户id
     */
    public long saveFileInfo(FileInfo user) {
        return fileInfoDao.insertOrReplace(user);
    }

    public void CleanFileInfo() {
         fileInfoDao.deleteAll();
    }
}
