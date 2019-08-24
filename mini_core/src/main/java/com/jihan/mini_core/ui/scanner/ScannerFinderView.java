package com.jihan.mini_core.ui.scanner;

import android.content.Context;
import android.graphics.Color;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * @author Jihan
 * @date 2019/8/24
 */
public class ScannerFinderView extends ViewFinderView {
    public ScannerFinderView(Context context) {
        super(context);
        mSquareViewFinder = true;
        mBorderPaint.setColor(Color.BLUE);
        mLaserPaint.setColor(Color.BLUE);
    }

}
