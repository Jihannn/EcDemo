package com.jihan.mini_core.ui.scanner;

import android.content.Context;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * @author Jihan
 * @date 2019/8/24
 */
public class ScanView extends ZBarScannerView {
    public ScanView(Context context) {
        super(context);
    }

    @Override
    protected IViewFinder createViewFinderView(Context context) {
        return new ScannerFinderView(context);
    }
}
