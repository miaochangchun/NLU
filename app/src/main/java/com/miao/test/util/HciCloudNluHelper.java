package com.miao.test.util;

import android.util.Log;

import com.sinovoice.hcicloudsdk.api.nlu.HciCloudNlu;
import com.sinovoice.hcicloudsdk.common.HciErrorCode;
import com.sinovoice.hcicloudsdk.common.Session;
import com.sinovoice.hcicloudsdk.common.nlu.NluConfig;
import com.sinovoice.hcicloudsdk.common.nlu.NluInitParam;
import com.sinovoice.hcicloudsdk.common.nlu.NluRecogResult;

/**
 * Created by 10048 on 2017/1/16.
 */
public class HciCloudNluHelper {
    private static final String TAG = HciCloudNluHelper.class.getSimpleName();
    private static HciCloudNluHelper mHciCloudNluHelper;

    public static HciCloudNluHelper getInstance() {
        if (mHciCloudNluHelper == null) {
            return  new HciCloudNluHelper();
        }
        return mHciCloudNluHelper;
    }

    private HciCloudNluHelper() {
    }

    /**
     * nlu初始化功能
     * @return  0成功，其他失败
     */
    public int init(){
        NluInitParam initParam = new NluInitParam();
        initParam.addParam(NluInitParam.PARAM_KEY_INIT_CAP_KEYS, ConfigUtil.NLU_CLOUD_RECOG);
        return HciCloudNlu.hciNluInit(initParam.getStringConfig());
    }

    /**
     * nlu反初始化功能
     * @return  0成功，其他失败
     */
    public int release(){
        return HciCloudNlu.hciNluRelease();
    }

    /**
     * nlu解析函数
     * @param text  需要解析的文本
     * @param intention 识别的领域
     * @param listener  识别结果回调类
     */
    public void recog(String text, String intention, OnNluRecogListener listener){
        Session session = new Session();
        NluConfig config = new NluConfig();
        config.addParam(NluConfig.SessionConfig.PARAM_KEY_CAP_KEY, ConfigUtil.NLU_CLOUD_RECOG);
        config.addParam(NluConfig.ResultConfig.PARAM_KEY_INTENTION, intention);
        int errCode = HciCloudNlu.hciNluSessionStart(config.getStringConfig(), session);
        if (errCode != HciErrorCode.HCI_ERR_NONE) {
            Log.e(TAG, "hciNluSessionStart failed and return " + errCode);
            listener.onError(errCode);
            return;
        }

        NluRecogResult recogResult = new NluRecogResult();
        errCode = HciCloudNlu.hciNluRecog(session, text, null, recogResult);
        if (errCode != HciErrorCode.HCI_ERR_NONE) {
            Log.e(TAG, "hciNluRecog failed and return " + errCode);
            listener.onError(errCode);
            return;
        } else {
            Log.e(TAG, "识别结果：" + recogResult.getRecogResultItemList().get(0).getResult());
            listener.onNluResult(recogResult);
        }

        errCode = HciCloudNlu.hciNluSessionStop(session);
        if (errCode != HciErrorCode.HCI_ERR_NONE) {
            Log.e(TAG, "hciNluSessionStop failed and return " + errCode);
            listener.onError(errCode);
            return;
        }
    }
}
