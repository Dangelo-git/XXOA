package com.dangelo.xxoa.mvp.main.third.tab;



import com.dangelo.xxoa.bean.dispatchDoc;
import com.dangelo.xxoa.bean.inDoc;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.BaseView;

import java.util.List;

/**
 * Created by luozhi on 2018/3/12.
 */

public class TtabContact {

    interface View extends BaseView {
        void showLoading(boolean isShow);
        void refreshData(List<inDoc> data);
    }
    interface Presenter extends BasePresenter {
        //        void order();
        void deteleRecord(int postion);
        void setType(int type);
        void GetDate(int page);

    }

}
