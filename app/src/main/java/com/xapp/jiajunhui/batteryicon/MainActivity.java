package com.xapp.jiajunhui.batteryicon;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.xapp.jjh.xui.activity.TopBarActivity;

public class MainActivity extends TopBarActivity {

    private BatteryView mBattery;
    private SeekBar mSeekBar;
    private RadioGroup mShowType,mHeadOrientation;

    @Override
    public View getContentView(LayoutInflater layoutInflater, ViewGroup container) {
        return inflate(R.layout.activity_main);
    }

    @Override
    public void findViewById() {
        mBattery = findView(R.id.battery);
        mSeekBar = findView(R.id.seekBar);
        mShowType = findView(R.id.rg_show_type);
        mHeadOrientation = findView(R.id.rg_head_orientation);
    }

    @Override
    public void initData() {
        setSwipeBackEnable(false);
        setNavigationVisible(false);
        setTopBarTitle("BatteryView");
        mSeekBar.setMax(100);
        mSeekBar.setProgress(50);
    }

    @Override
    public void setListener() {
        super.setListener();
        mShowType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_fill){
                    mBattery.setShowType(BatteryView.SHOW_TYPE_FILL);
                }else{
                    mBattery.setShowType(BatteryView.SHOW_TYPE_NUMBER);
                }
            }
        });
        mHeadOrientation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_left){
                    mBattery.setHeadOrientation(BatteryView.HEAD_ORIENTATION_LEFT);
                }else{
                    mBattery.setHeadOrientation(BatteryView.HEAD_ORIENTATION_RIGHT);
                }
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBattery.setBatteryValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
