package com.dangelo.xxoa;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dangelo.xxoa.Interf.ProgressCallback;
import com.dangelo.xxoa.bean.FileInfo;
import com.dangelo.xxoa.bean.Json;
import com.dangelo.xxoa.bean.download;
import com.dangelo.xxoa.json.SOAPStringB;
import com.dangelo.xxoa.net.API;
import com.dangelo.xxoa.net.MyDownLoader;
import com.dangelo.xxoa.net.MyJsonHttpResponseHandler;
import com.dangelo.xxoa.net.NetEngine;
import com.dangelo.xxoa.uitl.FileService;
import com.dangelo.xxoa.uitl.ShortCut;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class DocCumentDetailActivity extends BaseActivity implements ProgressCallback{
    private static String TAG = DocCumentDetailActivity.class.getName();

    private TextView meet_title_tv;
    private TextView docinfo_draftTitle_tv;
    private TextView docinfo_docno_tv;
    private TextView docinfo_princdeptname_tv;
    private TextView docinfo_creatorName_tv;
    private TextView docinfo_telephone_tv;
    private TextView docinfo_secretLevel_tv;
    private TextView docinfo_createdate_tv;
    private TextView docinfo_copysend_tv;
    private TextView docinfo_mainsend_tv;
    private TextView document_btn;
    private ProgressBar progressBar;
    private MyDownLoader dl;
    private FileInfo filepath;
    private  download Download;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentdetail);
        initToolbar("公文详情");
//        getData();
        dl= new MyDownLoader();
        dl.setCallback(this);
        initViews();

    }






    private void initViews() {
        meet_title_tv = (TextView) findViewById(R.id.meet_title_tv);
        docinfo_draftTitle_tv = (TextView) findViewById(R.id.docinfo_draftTitle_tv);
        docinfo_docno_tv = (TextView) findViewById(R.id.docinfo_docno_tv);
        docinfo_princdeptname_tv = (TextView) findViewById(R.id.docinfo_princdeptname_tv);
        docinfo_creatorName_tv = (TextView) findViewById(R.id.docinfo_creatorName_tv);
        docinfo_telephone_tv = (TextView) findViewById(R.id.docinfo_telephone_tv);
        docinfo_secretLevel_tv = (TextView) findViewById(R.id.docinfo_secretLevel_tv);
        docinfo_createdate_tv = (TextView) findViewById(R.id.docinfo_createdate_tv);
        docinfo_copysend_tv = (TextView) findViewById(R.id.docinfo_copysend_tv);
        docinfo_createdate_tv = (TextView) findViewById(R.id.docinfo_createdate_tv);
        docinfo_mainsend_tv = (TextView) findViewById(R.id.docinfo_mainsend_tv);
        document_btn = (TextView) findViewById(R.id.document_btn);
        progressBar = (ProgressBar) findViewById(R.id.firstBar);
        if(ShortCut.Docinfo!=null){
            meet_title_tv.setText(ShortCut.Docinfo.getDocTitle());
            docinfo_draftTitle_tv.setText(ShortCut.Docinfo.getDraftTitle());
            docinfo_docno_tv.setText(ShortCut.Docinfo.getDocNo());
            docinfo_princdeptname_tv.setText(ShortCut.Docinfo.getPrincDeptName());
            docinfo_creatorName_tv.setText(ShortCut.Docinfo.getCreatorName());
            docinfo_telephone_tv.setText(ShortCut.Docinfo.getTelephone());
            docinfo_secretLevel_tv.setText(ShortCut.Docinfo.getSecretLevel());
            docinfo_createdate_tv.setText(ShortCut.Docinfo.getCreateDate());
            docinfo_copysend_tv.setText(ShortCut.Docinfo.getCopySend());
            docinfo_mainsend_tv.setText(ShortCut.Docinfo.getMainSend());
            downloadRsq(ShortCut.Docinfo.getDocId());
            if(isAlresdlyDownFile()){
                document_btn.setText("打开公文");
            }
        }
        document_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (document_btn.getText().equals("下载公文")) {
                    GoDownFile(Download.getFile());
                } else if (document_btn.getText().equals("打开公文")) {
                    OpenFile();
                }
            }
        });


    }

    private void OpenFile() {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filepath.getFilepath()));
        intent.setDataAndType(uri, "application/msword");
        this.startActivity(intent);

    }

    private void downloadRsq(String docId) {
        Log.d(TAG, "downloadRsq docId"+docId);
//				json = getSubscribeJson();
//				Log.d(TAG, json.toString())
        Json json = new Json();
        json.setDocId(docId);
        json.setUuEName(ShortCut.getUser(this).getUserEnglishName());
        Gson gson = new Gson();
        String jsonStr = gson.toJson(json);

        if (ShortCut.getUser(this) == null) {
            ShortCut.showToast("请登录！", this);
            return;
        }
        NetEngine.submitPostTask(this, SOAPStringB.AssembleSoapRequest(API.Method_DOWNLOAD, jsonStr, ""), new MyJsonHttpResponseHandler() {
            @Override
            public void resultSuccess(Json result) throws JSONException {
                super.resultSuccess(result);
                Download = result.getDownload();
                Log.d(TAG, "result" + Download.getFile());
            }

            @Override
            public void resultFailure(Json result) throws JSONException {
                super.resultFailure(result);
            }
        });
    }
    private void GoDownFile(String requesl) {
//         requesl = "http://staff.ebupt.net/biaodan/ext_zhidu.doc";

        FileInfo info = new FileInfo();
        info.setFileName(ShortCut.Docinfo.getDocTitle());
        info.setFiletype("doc");
        info.setFileUrl(requesl);

        progressBar.setVisibility(View.VISIBLE);

        dl.downFile(info, this);

    }
    // 是否已经下载过该文件
    private boolean isAlresdlyDownFile() {
        FileService fileutils = new FileService();
        List<String> fileList = new ArrayList<>();
        fileList = fileutils.getDownloadFileList(this);
//        Log.i("文件名", fileList.size() + "");
        Log.i(TAG, "文件数" + fileList.size() + "");
        if (fileList.size() > 0) {
            for (int i = 0; i < fileList.size(); i++) {
                Log.i(TAG, "文件名" + fileList.get(i).toString());
                if(fileList.get(i).toString().equals(ShortCut.Docinfo.getDocTitle()+".doc")){
                    FileInfo file = new FileInfo();
                    file.setFileName(fileList.get(i).toString());
                    String FilePath = fileutils.getDownloadFilePath(this).trim() + fileList.get(i).toString();
                    file.setFilepath(FilePath);
                    filepath =file;
                    return true;
                }


            }

        }
        return false;
    }


    @Override
    public void DialogProgress(int count) {
        Log.i(TAG, "count:" + count);
        progressBar.setProgress(count);
        if (count > 98) {
            progressBar.setVisibility(View.INVISIBLE);


        }
    }

    @Override
    public void ProgressFinish(FileInfo info) {
        filepath = info;
        ShortCut.showToast("下载完成", this);
        document_btn.setText("打开公文");

    }

    @Override
    public void Progressfailure(FileInfo info) {
        progressBar.setProgress(0);
        progressBar.setVisibility(View.INVISIBLE);
        ShortCut.showToast("下载失败", this);

    }
}
