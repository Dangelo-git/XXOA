package com.dangelo.xxoa.mvp.base;

/**
 * Created by mga on 2017/6/29 18:49.
 */
public interface BasePresenter<V extends BaseView> {
    void start();

    /**
     * 在view生命周期oncreate中绑定view
     * 防止内存泄漏
     * @param view
     */
    void attach(V view);
    /**
     * 在view生命周期ondestroy中绑定view
     * 防止内存泄漏
     */
    void detach();
}
