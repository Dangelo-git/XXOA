package com.dangelo.xxoa.net;

import android.content.Context;
import android.util.Log;

import com.dangelo.xxoa.Interf.ProgressCallback;
import com.dangelo.xxoa.bean.FileInfo;
import com.dangelo.xxoa.uitl.DbUtil;
import com.dangelo.xxoa.uitl.FileService;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

import cz.msebera.android.httpclient.Header;


/**
 * Created by dangelo on 16/11/16.
 */
public class MyDownLoader {
    public static AsyncHttpClient client = new AsyncHttpClient();
    private static WeakReference<ProgressCallback> sCallback;

    // 下载apk
    public void downFile(final FileInfo info, final Context context) {
// 指定文件类型
        final ProgressCallback mcallback = getActiveCallback();
        String[] allowedContentTypes = new String[]{".*"};
// 获取二进制数据如图片和其他文件
        client.get(info.getFileUrl(), new BinaryHttpResponseHandler(
                allowedContentTypes) {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] binaryData) {


// 文件地址
                String filePath = FileService.getDownloadFilePath(context) + "/" + info.getFileName() + "." + info
                        .getFiletype();
// 下载成功后需要做的工作
                Log.e("binaryData:", "共下载了：" + binaryData.length);
                info.setFilepath(filePath);

                FileService fileutils = new FileService();

                fileutils.checkDir(context);


// 删除已下载的apk
                if (fileutils.isDownloadFileExist(filePath, context)) {
                    fileutils.deleteDownloadFile(filePath, context);
                }


                InputStream inputstream = new ByteArrayInputStream(binaryData);
                if (inputstream != null) {
                    fileutils.write2SDFromInput(context, filePath, inputstream);
                    try {
                        inputstream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mcallback.ProgressFinish(info);
                com.dangelo.xxoa.dao.FileInfo FileInfo= new com.dangelo.xxoa.dao.FileInfo();
                FileInfo.setFileName(info.getFileName());
                FileInfo.setFilepath(info.getFilepath());
                FileInfo.setFiletype(info.getFiletype());
                FileInfo.setFileUrl(info.getFileUrl());
                //保存数据库
                DbUtil.getInstance(context).saveFileInfo(FileInfo);
            }


            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] binaryData, Throwable error) {
                Log.i("http", error.getMessage());
                mcallback.Progressfailure(info);
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);
                if(mcallback==null){
                    Log.e("MyDownLoader","mcallback==null");
                }
                mcallback.DialogProgress(count);

// 下载进度显示
// progress.setProgress(count);


                Log.e("下载 Progress>>>>>", bytesWritten + " / " + totalSize);


            }

            @Override
            public void onRetry(int retryNo) {
// TODO Auto-generated method stub
                super.onRetry(retryNo);
// 返回重试次数
            }


        });
    }
    public static void setCallback(ProgressCallback mcallback) {
        sCallback = mcallback == null ? null : new WeakReference(mcallback);
    }

    public static ProgressCallback getCallback() {
        return sCallback == null ? null : (ProgressCallback) sCallback.get();
    }

    public static ProgressCallback getActiveCallback() {
        ProgressCallback callback = getCallback();
        return callback;
    }


}
