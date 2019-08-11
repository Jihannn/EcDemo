package com.jihan.mini_core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.net.callback.IError;
import com.jihan.mini_core.net.callback.IRequest;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.mini_core.util.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * @author Jihan
 * @date 2019/8/11
 */
public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private ISuccess mISuccess;

    public SaveFileTask(ISuccess mISuccess) {
        this.mISuccess = mISuccess;
    }

    @Override
    protected File doInBackground(Object... params) {
        String fileDir = (String) params[0];
        String extension = (String) params[1];
        ResponseBody responseBody = (ResponseBody) params[2];
        String name = (String) params[3];
        InputStream is = responseBody.byteStream();
        if (fileDir == null || "".equals(fileDir)) {
            fileDir = "Download_Dir";
        }
        if (extension == null || "".equals(extension)) {
            extension = "";
        }
        if (name == null) {
            return FileUtil.writeToDisk(is, fileDir, extension.toUpperCase(), extension);
        }
        return FileUtil.writeToDisk(is,fileDir,extension);
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if(mISuccess != null){
            mISuccess.success(file.getPath());
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if("apk".equals(FileUtil.getExtension(file.getPath()))){
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Mini.getApplicationContext().startActivity(intent);
        }
    }
}
