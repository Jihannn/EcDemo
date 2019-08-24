package com.jihan.mini_core.ui.scanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jihan.mini_core.callback.CallBackManager;
import com.jihan.mini_core.callback.CallBackType;
import com.jihan.mini_core.callback.IGlobalCallBack;
import com.jihan.mini_core.delegates.MiniDelegate;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * @author Jihan
 * @date 2019/8/24
 */
public class ScannerDelegate extends MiniDelegate implements ZBarScannerView.ResultHandler {

    private ScanView mScanView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mScanView == null) {
            mScanView = new ScanView(getContext());
        }
        mScanView.setAutoFocus(true);
        mScanView.setResultHandler(this);
    }

    @Override
    public Object setLayout() {
        return mScanView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mScanView != null) {
            mScanView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mScanView != null) {
            mScanView.stopCamera();
            mScanView.stopCameraPreview();
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    @Override
    public void handleResult(Result rawResult) {
        IGlobalCallBack callBack = CallBackManager
                .getInstance()
                .getCallBack(CallBackType.SCANNER);
        if (callBack != null) {
            callBack.execute(rawResult.getContents());
        }
        getSupportDelegate().pop();
    }
}
