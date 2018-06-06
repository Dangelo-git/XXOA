package com.dangelo.xxoa.mvp.knowledge;

import com.dangelo.xxoa.bean.Rqtknowledge;
import com.dangelo.xxoa.bean.knowledgeCategories;
import com.dangelo.xxoa.bean.knowledgePersonal;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.base.BaseView;

import java.util.List;


/**
 * Created by mga on 2017/8/11 17:26.
 */

public class KnowLdlistContact {
    interface View extends BaseView {
        void showLoading(boolean isShow);



        void refreshData(List<knowledgePersonal> data);


    }
    interface Presenter extends BasePresenter {
        void GetDate(int page,Rqtknowledge mRqtknowledge);
    }
}
