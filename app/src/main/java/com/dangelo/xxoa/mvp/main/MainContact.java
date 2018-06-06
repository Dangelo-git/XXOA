package com.dangelo.xxoa.mvp.main;

import android.os.Bundle;


import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.BaseView;
import com.umeng.fb.model.UserInfo;

import java.util.List;

/**
 * Created by mga on 2017/7/10 10:56.
 */
public class MainContact {

  interface  View extends BaseView {
      void setNumber(String number);
      void setDate(String FolderSize);
      void setUpdate(String Androidv,boolean UpdateFlag);
      void setLoadFilenum(int LoadFilenum);
      void doDownLoad(String Androidvdurl);

  }
  interface Presenter extends BasePresenter {
        String StringData();

      void doDownLoad();
      void setValue();
      void LoadFilenum();
      void LogOutOnClick();
  }
}
