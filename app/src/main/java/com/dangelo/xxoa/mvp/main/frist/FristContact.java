package com.dangelo.xxoa.mvp.main.frist;


import com.dangelo.xxoa.bean.Broese;
import com.dangelo.xxoa.bean.dispatchDoc;
import com.dangelo.xxoa.bean.noticeInfo;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.BaseView;

import java.util.List;

/**
 * Created by mga on 2017/7/11 18:23.
 */
public class FristContact {
    public interface FristView extends BaseView<FristPresenter> {
        void showErrinfo(boolean isshow, String errinfo);
        void showFailReason(int error);

        void showProgressDialog(boolean isShow, String content);


        void setNumber(String sendnumber,String receivenumber,String meetnumber);
//        void buildBroeseList(List<Broese> broesenews);
        void refreshData(List<noticeInfo> data);


    }
    public interface FristPresenter extends BasePresenter {
        void showErrinfo();

        void GetDate(int page);
        void GetUaMessageNum();

    }
}
