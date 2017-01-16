package com.miao.test.nlu;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.miao.test.util.HciCloudNluHelper;
import com.miao.test.util.HciCloudSysHelper;
import com.miao.test.util.OnNluRecogListener;
import com.sinovoice.hcicloudsdk.common.HciErrorCode;
import com.sinovoice.hcicloudsdk.common.nlu.NluRecogResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText etInput;
    private Button btnRecog;
    private HciCloudSysHelper mHciCloudSysHelper;
    private HciCloudNluHelper mHciCloudNluHelper;
    private List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = (EditText) findViewById(R.id.et_input);
        btnRecog = (Button) findViewById(R.id.btn_recog);

        btnRecog.setOnClickListener(this);

        initView();

        int errCode = initSinovoice();
        if (errCode != HciErrorCode.HCI_ERR_NONE) {
            Toast.makeText(this, "灵云系统初始化失败，错误码=" + errCode, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 初始化checkbox
     */
    private void initView() {
        CheckBox cbApp = (CheckBox) findViewById(R.id.app);
        cbApp.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbBaike = (CheckBox) findViewById(R.id.baike);
        cbBaike.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbCalendar = (CheckBox) findViewById(R.id.calendar);
        cbCalendar.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbCarCt = (CheckBox) findViewById(R.id.carControl);
        cbCarCt.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbContacts = (CheckBox) findViewById(R.id.contacts);
        cbContacts.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbCook = (CheckBox) findViewById(R.id.cookbook);
        cbCook.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbdeviceCt = (CheckBox) findViewById(R.id.deviceControl);
        cbdeviceCt.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbExc = (CheckBox) findViewById(R.id.exchangeRate);
        cbExc.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbFlight = (CheckBox) findViewById(R.id.flight);
        cbFlight.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbHimalaya = (CheckBox) findViewById(R.id.Himalayan);
        cbHimalaya.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbHotel = (CheckBox) findViewById(R.id.hotel);
        cbHotel.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbJoke = (CheckBox) findViewById(R.id.joke);
        cbJoke.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbMap = (CheckBox) findViewById(R.id.map);
        cbMap.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbMessage = (CheckBox) findViewById(R.id.message);
        cbMessage.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbMusic = (CheckBox) findViewById(R.id.music);
        cbMusic.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbMusicControl = (CheckBox) findViewById(R.id.musicControl);
        cbMusicControl.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbNews = (CheckBox) findViewById(R.id.news);
        cbNews.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbRes = (CheckBox) findViewById(R.id.restaurant);
        cbRes.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbRadio = (CheckBox) findViewById(R.id.radio);
        cbRadio.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbSch = (CheckBox) findViewById(R.id.schedule);
        cbSch.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbSea = (CheckBox) findViewById(R.id.search);
        cbSea.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbStock = (CheckBox) findViewById(R.id.stock);
        cbStock.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbStory = (CheckBox) findViewById(R.id.story);
        cbStory.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbTrain = (CheckBox) findViewById(R.id.train);
        cbTrain.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbTrans = (CheckBox) findViewById(R.id.translation);
        cbTrans.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbVideo = (CheckBox) findViewById(R.id.video);
        cbVideo.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbVideoCt = (CheckBox) findViewById(R.id.videoControl);
        cbVideoCt.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbWeather = (CheckBox) findViewById(R.id.weather);
        cbWeather.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbWeb = (CheckBox) findViewById(R.id.website);
        cbWeb.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbWeibo = (CheckBox) findViewById(R.id.weibo);
        cbWeibo.setOnCheckedChangeListener(myCheckChangelistener);
        CheckBox cbYellow = (CheckBox) findViewById(R.id.yellowpage);
        cbYellow.setOnCheckedChangeListener(myCheckChangelistener);
    }

    private CompoundButton.OnCheckedChangeListener myCheckChangelistener = new CompoundButton.OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.app:
                    if (isChecked == true) {
                        list.add("app");
                    } else {
                        list.remove("app");
                    }
                    break;

                case R.id.baike:
                    if (isChecked == true) {
                        list.add("baike");
                    } else {
                        list.remove("baike");
                    }
                    break;

                case R.id.calendar:
                    if (isChecked == true) {
                        list.add("calendar");
                    } else {
                        list.remove("calendar");
                    }
                    break;

                case R.id.carControl:
                    if (isChecked == true) {
                        list.add("carControl");
                    } else {
                        list.remove("carControl");
                    }
                    break;

                case R.id.contacts:
                    if (isChecked == true) {
                        list.add("contacts");
                    } else {
                        list.remove("contacts");
                    }
                    break;

                case R.id.cookbook:
                    if (isChecked == true) {
                        list.add("cookbook");
                    } else {
                        list.remove("cookbook");
                    }
                    break;

                case R.id.deviceControl:
                    if (isChecked == true) {
                        list.add("deviceControl");
                    } else {
                        list.remove("deviceControl");
                    }
                    break;

                case R.id.exchangeRate:
                    if (isChecked == true) {
                        list.add("exchangeRate");
                    } else {
                        list.remove("exchangeRate");
                    }
                    break;

                case R.id.flight:
                    if (isChecked == true) {
                        list.add("flight");
                    } else {
                        list.remove("flight");
                    }
                    break;

                case R.id.Himalayan:
                    if (isChecked == true) {
                        list.add("Himalayan");
                    } else {
                        list.remove("Himalayan");
                    }
                    break;

                case R.id.hotel:
                    if (isChecked == true) {
                        list.add("hotel");
                    } else {
                        list.remove("hotel");
                    }
                    break;

                case R.id.joke:
                    if (isChecked == true) {
                        list.add("joke");
                    } else {
                        list.remove("joke");
                    }
                    break;

                case R.id.map:
                    if (isChecked == true) {
                        list.add("map");
                    } else {
                        list.remove("map");
                    }
                    break;

                case R.id.message:
                    if (isChecked == true) {
                        list.add("message");
                    } else {
                        list.remove("message");
                    }
                    break;

                case R.id.music:
                    if (isChecked == true) {
                        list.add("music");
                    } else {
                        list.remove("music");
                    }
                    break;

                case R.id.musicControl:
                    if (isChecked == true) {
                        list.add("musicControl");
                    } else {
                        list.remove("musicControl");
                    }
                    break;

                case R.id.news:
                    if (isChecked == true) {
                        list.add("news");
                    } else {
                        list.remove("news");
                    }
                    break;

                case R.id.restaurant:
                    if (isChecked == true) {
                        list.add("restaurant");
                    } else {
                        list.remove("restaurant");
                    }
                    break;

                case R.id.radio:
                    if (isChecked == true) {
                        list.add("radio");
                    } else {
                        list.remove("radio");
                    }
                    break;

                case R.id.schedule:
                    if (isChecked == true) {
                        list.add("schedule");
                    } else {
                        list.remove("shedule");
                    }
                    break;

                case R.id.search:
                    if (isChecked == true) {
                        list.add("search");
                    } else {
                        list.remove("search");
                    }
                    break;

                case R.id.stock:
                    if (isChecked == true) {
                        list.add("stock");
                    } else {
                        list.remove("stock");
                    }
                    break;

                case R.id.story:
                    if (isChecked == true) {
                        list.add("story");
                    } else {
                        list.remove("story");
                    }
                    break;

                case R.id.train:
                    if (isChecked == true) {
                        list.add("train");
                    } else {
                        list.remove("train");
                    }
                    break;

                case R.id.translation:
                    if (isChecked == true) {
                        list.add("translation");
                    } else {
                        list.remove("translation");
                    }
                    break;

                case R.id.video:
                    if (isChecked == true) {
                        list.add("video");
                    } else {
                        list.remove("video");
                    }
                    break;

                case R.id.videoControl:
                    if (isChecked == true) {
                        list.add("videoControl");
                    } else {
                        list.remove("videoControl");
                    }
                    break;

                case R.id.weather:
                    if (isChecked == true) {
                        list.add("weather");
                    } else {
                        list.remove("weather");
                    }
                    break;

                case R.id.website:
                    if (isChecked == true) {
                        list.add("website");
                    } else {
                        list.remove("website");
                    }
                    break;

                case R.id.weibo:
                    if (isChecked == true) {
                        list.add("weibo");
                    } else {
                        list.remove("weibo");
                    }
                    break;

                case R.id.yellowpage:
                    if (isChecked == true) {
                        list.add("yellowpage");
                    } else {
                        list.remove("yellowpage");
                    }
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        if (mHciCloudNluHelper != null) {
            mHciCloudNluHelper.release();
        }
        if (mHciCloudSysHelper != null) {
            mHciCloudSysHelper.release();
        }
        super.onDestroy();
    }

    /**
     * 灵云系统初始化
     * @return
     */
    private int initSinovoice() {
        mHciCloudSysHelper = HciCloudSysHelper.getInstance();
        mHciCloudNluHelper = HciCloudNluHelper.getInstance();

        int errCode = mHciCloudSysHelper.init(this);
        if (errCode != HciErrorCode.HCI_ERR_NONE) {
            return errCode;
        }
        errCode = mHciCloudNluHelper.init();
        if (errCode != HciErrorCode.HCI_ERR_NONE) {
            return errCode;
        }
        return HciErrorCode.HCI_ERR_NONE;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_recog:
                if ("".equals(etInput.getText().toString()) || etInput.equals(null)) {
                    Toast.makeText(this, "输入为空，重新输入", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (list.size() == 0) {
                    Toast.makeText(this, "请至少勾选一个领域", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder sBuilder = new StringBuilder();
                for (int i=0; i<list.size(); i++) {
                    sBuilder.append(list.get(i)).append(";");
                }
                Log.d(TAG, "StringBuilder = " + sBuilder.toString());
                mHciCloudNluHelper.recog(etInput.getText().toString(), sBuilder.toString(), new OnNluRecogListener() {
                    @Override
                    public void onNluResult(NluRecogResult nluRecogResult) {
                        String result = nluRecogResult.getRecogResultItemList().get(0).getResult();
                        showDialog(etInput.getText().toString(), result);
                    }

                    @Override
                    public void onError(int errorCode) {
                        Toast.makeText(MainActivity.this, "识别错误，错误码=" + errorCode, Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            default:
                break;
        }
    }

    /**
     * 弹出对话框
     * @param title 对话框标题
     * @param message   对话框内容
     */
    private void showDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确认", null);
        builder.create().show();
    }
}
