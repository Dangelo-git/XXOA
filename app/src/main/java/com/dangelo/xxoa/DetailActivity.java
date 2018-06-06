package com.dangelo.xxoa;

import android.os.Bundle;

/**
 * Created by Administrator on 2016/6/28.
 */
public class DetailActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initToolbar("");
//        getData();
        initViews();

        initOnClick();
    }






    private void initViews() {
//        nameTv = (TextView) findViewById(R.id.package_name);
//        priceTv = (TextView) findViewById(R.id.price);
//        infoTv = (TextView) findViewById(R.id.package_info);

//        nameTv.setText(mPackageList.getPackage_name());
//        Log.d(TAG, "name" + mPackageList.getPackage_name());
//        priceTv.setText(mPackageList.getPackage_price());
//        infoTv.setText(mPackageList.getPackage_info());

    }

    private void initOnClick() {

    }

//    private Json getSubscribeJson() {
//        //订购流程
//        Json json = new Json();
//        json.setBindnumber(number);
//        json.setPassword(md5password);
//        json.setRegistration_id(MyApplication.getInstance().getRegistration_id());
//
//        List<Business_list> business_lists = null;
//        List<Package_list> packageLists = null;
//        if (business_lists == null) {
//            business_lists = new ArrayList<Business_list>();
//        }
//        if (packageLists == null) {
//            packageLists = new ArrayList<Package_list>();
//        }
//        Business_list business_list = new Business_list();
//        business_list.setBusiness_code("0001");
//        Package_list packageList = new Package_list();
//        packageList.setPackage_code(mPackageList.getPackage_code());
//        returnDate();
//        packageList.setPackage_startdate(startDate);
//        packageLists.add(packageList);
//        business_list.setPackageList(packageLists);
//        business_lists.add(business_list);
//        json.setBusiness_list(business_lists);
//        // packageLists.clear();
//        return json;
//    }






}
