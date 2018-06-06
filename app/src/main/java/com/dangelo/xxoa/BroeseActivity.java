package com.dangelo.xxoa;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.uitl.ShortCut;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2016/6/28.
 */
public class BroeseActivity extends BaseActivity {

private TextView content;
private TextView title;
private ImageView image;
private ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broese);
        imageLoader = ImageLoader.getInstance();
        initToolbar("详情");
        initViews();
        getData();
    }

    private void getData() {
        image.setVisibility(View.GONE);
        if(ShortCut.broese!=null){
            content.setText("\u3000\u3000"+ShortCut.broese.getContent() );
            title.setText(ShortCut.broese.getTitle() );
            if(ShortCut.broese.getImgUrl()!=null&&!ShortCut.broese.getImgUrl().equals("")&&!ShortCut.broese.getImgUrl().equals("null")){
                image.setVisibility(View.VISIBLE);
                imageLoader.displayImage(NetEngine.BASE_URL+ShortCut.broese.getImgUrl(), image, ShortCut.getImageOptions());
            }
        }

    }


    private void initViews() {
        content = (TextView) findViewById(R.id.broese_content);
        title = (TextView) findViewById(R.id.broese_title);
        image = (ImageView) findViewById(R.id.broese_image);

    }





}
