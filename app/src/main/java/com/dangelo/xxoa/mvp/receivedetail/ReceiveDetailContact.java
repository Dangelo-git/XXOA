package com.dangelo.xxoa.mvp.receivedetail;

import com.dangelo.xxoa.bean.currentStation;
import com.dangelo.xxoa.bean.inDoc;
import com.dangelo.xxoa.bean.nextStation;
import com.dangelo.xxoa.bean.node;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.BaseView;

import java.util.List;


/**
 * Created by mga on 2017/8/11 17:26.
 */

public class ReceiveDetailContact {
    interface View extends BaseView {
        void showLoading(boolean isShow);


        void showFailReason(String error);
        void showProgressDialog(boolean isShow, String content);
        void showPopDialog(List<node> data);
        void showPopDialogPerson(List<currentStation> data);



    }
    interface Presenter extends BasePresenter {
        void getSendDetail();
        void DoSave(inDoc minDoc);
        void DoSubmit();
        void DoReset();
        void DoOpen();
         void getNode(Long id);
        void getCurrentStation(String stationId , String signbatchtypeid );
        void PostnextUserActiviti(nextStation nextstation);
    }
}
