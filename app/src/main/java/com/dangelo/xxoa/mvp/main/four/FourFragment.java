package com.dangelo.xxoa.mvp.main.four;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;


import com.dangelo.xxoa.R;
import com.dangelo.xxoa.mvp.base.BaseFragment;
import com.dangelo.xxoa.mvp.base.BasePresenter;
import com.dangelo.xxoa.mvp.main.MainActivity;
import com.dangelo.xxoa.mvp.main.four.tab.Fourtab1Fragment;
import com.dangelo.xxoa.mvp.main.four.tab.Fourtab2Fragment;
import com.dangelo.xxoa.ui.CustomViewPager;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.jiongbull.jlog.JLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lz.
 * @date on 2017/10/23 18:04.
 */

public class FourFragment extends BaseFragment implements FourContact.View,View.OnClickListener{

    private NavigationTabStrip navPackageTab;
    private CustomViewPager vpPackageShow;
    private final String TAG=FourFragment.class.getSimpleName();
    private Fourtab1Fragment myAccontFragment;
    private Fourtab2Fragment myAccontFragment2;
    private List<Fragment> fragmentList;
    private boolean isActive;
    private Context mContext;


    public static FourFragment newInstance(int sectionNumber) {
        FourFragment fragment = new FourFragment();
        Bundle args = new Bundle();
        args.putInt(MainActivity.ARG_SECTION_NUMBER, sectionNumber);
        Log.d("Main", "newInstance:" + sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        JLog.d(TAG,"* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *"+TAG+"onCreate");
        mContext = getActivity().getApplicationContext();

//        myPackagePresenter.getUserAllPackageInfo();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFragment();
        initTabAndViewPager(isActive);
    }

    private void initTabAndViewPager(Boolean isisActive) {
        vpPackageShow.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }


        });
        if(isisActive){
            navPackageTab.setViewPager(vpPackageShow, 1);
        }else {
            navPackageTab.setViewPager(vpPackageShow, 0);
        }
        navPackageTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                JLog.d(TAG,"* * position:"+position);
//                switch (position) {
//                    case 0:
//                        if(myAccontFragment!=null){
//                            myAccontFragment.refreshData();
//                        }
//
//                        break;
//                    case 1:
//                        if(myCardPackageFragment!=null){
//                            myCardPackageFragment.refreshData();
//                        }
//
//                        break;
//                    case 2:
//                        if(myAccontFragment!=null){
//                            myAccontFragment2.refreshData();
//                        }
//
//                        break;
//                    case 3:
//                        if(myAccontFragment!=null){
//                            myAccontFragment3.refreshData();
//                        }
//
//                        break;
//                    case 4:
//                        if(myAccontFragment!=null){
//                            myAccontFragment4.refreshData();
//                        }
//
//                        break;

//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initFragment() {

        myAccontFragment = new Fourtab1Fragment();
        myAccontFragment2 = new Fourtab2Fragment();
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
            fragmentList.add(myAccontFragment);
            fragmentList.add(myAccontFragment2);
        }else{
            fragmentList.clear();
            fragmentList.add(myAccontFragment);
            fragmentList.add(myAccontFragment2);

        }
    }

    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        // right_btn.setVisibility(View.GONE);



//		}
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG, "onHiddenChanged" + hidden);
        if (!hidden) {


//			LogOutOnClick();
        }
    }




    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new FourPresenter(getActivity());
        mPresenter.start();
        return mPresenter;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        navPackageTab = (NavigationTabStrip) view.findViewById(R.id.nv_package_tab);
        vpPackageShow = (CustomViewPager) view.findViewById(R.id.vp_package_show);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {



            default:
                break;
        }

    }


    public void refreshMyAccont(){
        if(myAccontFragment!=null){
            myAccontFragment.refreshData();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        JLog.d(TAG,"* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *"+TAG+"onDestroy");
    }

    @Override
    protected int bindLayout() {
        return  R.layout.mvp_fragment_four;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
    public void setShowpage(int showpage) {
        vpPackageShow.setCurrentItem(showpage);
    }
}
