package com.miao.test.util;

import com.sinovoice.hcicloudsdk.common.nlu.NluRecogResult;

/**
 * Created by 10048 on 2017/1/16.
 */
public interface OnNluRecogListener {
    /**
     * Nlu解析正确结果回调
     * @param nluRecogResult
     */
    void onNluResult(NluRecogResult nluRecogResult);

    /**
     * Nlu解析结果错误回调
     * @param errorCode
     */
    void onError(int errorCode);

}
