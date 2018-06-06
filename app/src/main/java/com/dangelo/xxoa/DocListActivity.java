package com.dangelo.xxoa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dangelo.xxoa.adapter.DocListAdapter;
import com.dangelo.xxoa.bean.FileInfo;
import com.dangelo.xxoa.uitl.FileService;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class DocListActivity extends BaseActivity {
    private static String TAG = DocListActivity.class.getName();

    private DocListAdapter mDocAdapter;
    private List<FileInfo> filelist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc);
        initViews();
        initToolbar("下载列表");
        LoadFilenum();
        buildList();


    }

    private void intidate() {

    }
    // 获取下载文件数
    private void LoadFilenum() {
        FileService fileutils = new FileService();
        List<String> fileList = new ArrayList<>();
        fileList = fileutils.getDownloadFileList(this);
//        Log.i("文件名", fileList.size() + "");
        Log.i(TAG, "文件数"+fileList.size() + "");
        if (fileList.size() > 0) {
            for (int i = 0; i < fileList.size(); i++) {
                if(fileList.get(i).toString().equals("File")){
                    break;
                }
                FileInfo file = new FileInfo();
                file.setFileName(fileList.get(i).toString());
                String FilePath = fileutils.getDownloadFilePath(this).trim() + fileList.get(i).toString();
                file.setFilepath(FilePath);
                Log.i(TAG, "文件名"+fileList.get(i).toString());

                filelist.add(file);
            }
        }

    }

    private void buildList() {
        // TODO Auto-generated method stub
        ListView keyListView = (ListView) findViewById(R.id.filelist_lv);

        mDocAdapter = new DocListAdapter(DocListActivity.this, filelist);
        keyListView.setAdapter(mDocAdapter);

//        keyListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        keyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg,
                                    long arg3) {
                OpenFile(filelist.get(arg).getFilepath());

            }
        });

    }
    private void OpenFile(String FilePath) {
        Log.i(TAG, "文件地址"+FilePath);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(FilePath));
        intent.setDataAndType(uri, "application/msword");
        this.startActivity(intent);

    }
    private void initViews() {

    }
}
