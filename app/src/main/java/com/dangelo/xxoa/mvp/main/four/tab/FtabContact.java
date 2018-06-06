package com.dangelo.xxoa.mvp.main.four.tab;



import com.dangelo.xxoa.bean.ExMeet;
import com.dangelo.xxoa.bean.InnerMeet;
import com.dangelo.xxoa.bean.SysMsg;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.BaseView;

import java.util.List;

/**
 * Created by luozhi on 2018/3/12.
 */

public class FtabContact {

    interface View extends BaseView {
        void showLoading(boolean isShow);
        void refreshData(List<InnerMeet> data);
        void refreshData2(List<ExMeet> data);
    }
    interface Presenter extends BasePresenter {
        //        void order();
        void deteleRecord(int postion);
        void setType(int type);
        void GetDate(int page);
    }

}
