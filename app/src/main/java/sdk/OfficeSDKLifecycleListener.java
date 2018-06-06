package sdk;

import com.ntko.app.support.LifecycleListenerImpl;
import com.ntko.app.support.Params;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class OfficeSDKLifecycleListener extends LifecycleListenerImpl {

    @Override
    public void onStartOpenDocument(String filePath, boolean isEncrypted) {
        System.out.println("打开文件 ===> " + filePath);
    }

    @Override
    public void onDocumentOpened(int docType, Params.RawFileType fileType) {
        System.out.println("文件打开成功");
    }

    @Override
    public void onDocumentOpenFailed(Throwable ex) {
        System.out.println("文件打开失败: " + ex.getMessage());
    }

    @Override
    public void onDocumentClosed(String localPath, boolean isModified) {
        System.out.println("文件已经关闭：" + localPath + "，变动: " + isModified);
    }

    @Override
    public void onSaveDocument(String localPath) {
        System.out.println("文件保存成功 ===> " + localPath);
    }

    @Override
    public void onStartUpload(String any, String path, String title, String uploadUrl) {
        System.out.println("文件开始上传 ===> " + uploadUrl);
    }

    @Override
    public void onUploadCanceled(String filePath, String uploadURL, String reason) {
        System.out.println("文件取消 ===> filePath: " + filePath);
        System.out.println("文件取消 ===> uploadURL: " + uploadURL);
        System.out.println("文件取消 ===> reason: " + reason);
    }

    @Override
    public void onUploadFailed(String filePath, String failure, int code, Throwable cause) {
        System.out.println("文件上传失败 ===> filePath: " + filePath);
        System.out.println("文件上传失败 ===> failure: " + failure);
        System.out.println("文件上传失败 ===> code: " + code);
        if (cause != null) {
            cause.printStackTrace();
        }
    }

    @Override
    public void onUploadSucceed(String filePath, String responseString) {
        System.out.println("文件上传成功");
    }

    @Override
    public void onAddStampToDocument(String user, int index, File stamp, File page) {
        System.out.println("添加批注到PDF文档，用户：" + user + "，批注图像文件：" + stamp+ "，页面图像文件：" + page);
    }

    @Override
    public void onDownloadStart(String address) {
        System.out.println("文件下载开始 ===> " + address);
    }

    @Override
    public void onDownloadFailed(int errorCode, String errorMessage, Throwable cause) {
        System.out.println("文件下载失败 ===> " + errorCode + ":" + errorMessage);
        if (cause != null) {
            cause.printStackTrace();
        }
    }

    @Override
    public void onDownloadComplete(String path) {
        System.out.println("文件下载成功 ===> " + path);
    }

    @Override
    public Map<String, String> getAnnotationProperties() {
        return mAnnotationProperties;
    }

    @Override
    public Map<String, String> getAnnotationPropertyNames() {
        return mAnnotationPropertyNames;
    }

    private final Map<String, String> mAnnotationProperties = new HashMap<>();
    private final Map<String, String> mAnnotationPropertyNames = new HashMap<>();
}