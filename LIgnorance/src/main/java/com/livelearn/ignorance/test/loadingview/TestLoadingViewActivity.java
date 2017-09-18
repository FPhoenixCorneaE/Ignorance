package com.livelearn.ignorance.test.loadingview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.ldoublem.loadingviewlib.LVChromeLogo;
import com.ldoublem.loadingviewlib.LVCircular;
import com.ldoublem.loadingviewlib.LVCircularCD;
import com.ldoublem.loadingviewlib.LVLineWithText;
import com.ldoublem.loadingviewlib.view.LVBattery;
import com.ldoublem.loadingviewlib.view.LVBlazeWood;
import com.ldoublem.loadingviewlib.view.LVBlock;
import com.ldoublem.loadingviewlib.view.LVCircularJump;
import com.ldoublem.loadingviewlib.view.LVCircularRing;
import com.ldoublem.loadingviewlib.view.LVCircularSmile;
import com.ldoublem.loadingviewlib.view.LVCircularZoom;
import com.ldoublem.loadingviewlib.view.LVEatBeans;
import com.ldoublem.loadingviewlib.view.LVFinePoiStar;
import com.ldoublem.loadingviewlib.view.LVFunnyBar;
import com.ldoublem.loadingviewlib.view.LVGears;
import com.ldoublem.loadingviewlib.view.LVGearsTwo;
import com.ldoublem.loadingviewlib.view.LVGhost;
import com.ldoublem.loadingviewlib.view.LVNews;
import com.ldoublem.loadingviewlib.view.LVPlayBall;
import com.ldoublem.loadingviewlib.view.LVRingProgress;
import com.ldoublem.loadingviewlib.view.LVWifi;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created on 2017/6/20.
 */

public class TestLoadingViewActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.lv_circular_CD)
    LVCircularCD lvCircularCD;

    @BindView(R.id.lv_circular_ring)
    LVCircularRing lvCircularRing;

    @BindView(R.id.lv_circular)
    LVCircular lvCircular;

    @BindView(R.id.lv_fine_pointed_star)
    LVFinePoiStar lvFinePointedStar;

    @BindView(R.id.lv_circular_smile)
    LVCircularSmile lvCircularSmile;

    @BindView(R.id.lv_gears)
    LVGears lvGears;

    @BindView(R.id.lv_gears_two)
    LVGearsTwo lvGearsTwo;

    @BindView(R.id.lv_wifi)
    LVWifi lvWifi;

    @BindView(R.id.lv_circular_jump)
    LVCircularJump lvCircularJump;

    @BindView(R.id.lv_circular_zoom)
    LVCircularZoom lvCircularZoom;

    @BindView(R.id.lv_play_ball)
    LVPlayBall lvPlayBall;

    @BindView(R.id.lv_news)
    LVNews lvNews;

    @BindView(R.id.lv_line_text)
    LVLineWithText lvLineText;

    @BindView(R.id.lv_eat_beans)
    LVEatBeans lvEatBeans;

    @BindView(R.id.lv_chromeLogo)
    LVChromeLogo lvChromeLogo;

    @BindView(R.id.lv_ring_progress)
    LVRingProgress lvRingProgress;

    @BindView(R.id.lv_block)
    LVBlock lvBlock;

    @BindView(R.id.lv_funny_bar)
    LVFunnyBar lvFunnyBar;

    @BindView(R.id.lv_ghost)
    LVGhost lvGhost;

    @BindView(R.id.lv_blaze_wood)
    LVBlazeWood lvBlazeWood;

    @BindView(R.id.lv_battery)
    LVBattery lvBattery;

    @BindView(R.id.btn_start_all)
    Button btnStartAll;

    @BindView(R.id.btn_stop_all)
    Button btnStopAll;

    private Timer mTimerLVLineWithText = new Timer();// 定时器
    private Timer mTimerLVNews = new Timer();// 定时器

    private int mValueLVLineWithText = 0;
    private int mValueLVNews = 0;

    private LoadingHandler mHandler = new LoadingHandler(this);

    private static class LoadingHandler extends Handler {
        private WeakReference<Context> weakReference;

        LoadingHandler(Context context) {
            weakReference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            TestLoadingViewActivity activity = (TestLoadingViewActivity) weakReference.get();
            if (activity != null) {
                if (msg.what == 2)
                    activity.lvLineText.setValue(msg.arg1);
                else if (msg.what == 1) {
                    activity.lvNews.setValue(msg.arg1);
                }
            }
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_loading_view;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        lvCircularCD.setViewColor(Color.rgb(0, 255, 0));

        lvCircularRing.setViewColor(Color.argb(100, 255, 255, 255));
        lvCircularRing.setBarColor(Color.YELLOW);

        lvCircular.setViewColor(Color.rgb(255, 99, 99));
        lvCircular.setRoundColor(Color.rgb(255, 0, 0));

        lvFinePointedStar.setViewColor(Color.WHITE);
        lvFinePointedStar.setCircleColor(Color.YELLOW);
        lvFinePointedStar.setDrawPath(false);

        lvCircularSmile.setViewColor(Color.rgb(144, 238, 146));

        lvGears.setViewColor(Color.rgb(55, 155, 233));

        lvGearsTwo.setViewColor(Color.rgb(155, 55, 233));

        lvWifi.setViewColor(Color.BLACK);

        lvCircularJump.setViewColor(Color.rgb(133, 66, 99));

        lvCircularZoom.setViewColor(Color.rgb(255, 0, 122));

        lvPlayBall.setViewColor(Color.WHITE);
        lvPlayBall.setBallColor(Color.RED);

        lvNews.setViewColor(Color.WHITE);

        lvLineText.setViewColor(Color.rgb(33, 66, 77));
        lvLineText.setTextColor(Color.rgb(233, 166, 177));

        lvEatBeans.setViewColor(Color.WHITE);
        lvEatBeans.setEyeColor(Color.BLUE);

        lvRingProgress.setViewColor(Color.WHITE);
        lvRingProgress.setTextColor(Color.BLACK);
        lvRingProgress.setPorBarStartColor(Color.YELLOW);
        lvRingProgress.setPorBarEndColor(Color.BLUE);

        lvBlock.setViewColor(Color.rgb(245, 209, 22));
        lvBlock.setShadowColor(Color.BLACK);
        lvBlock.isShadow(false);

        lvFunnyBar.setViewColor(Color.rgb(234, 167, 107));

        lvGhost.setViewColor(Color.WHITE);
        lvGhost.setHandColor(Color.BLACK);

        lvBattery.setBatteryOrientation(LVBattery.BatteryOrientation.VERTICAL);//LVBattery.BatteryOrientation.HORIZONTAL
        lvBattery.setShowNum(true);
        lvBattery.setViewColor(Color.WHITE);
        lvBattery.setCellColor(Color.GREEN);

    }

    @Override
    public void setListeners() {

    }

    @OnClick({R.id.lv_circular_CD, R.id.lv_circular_ring, R.id.lv_circular, R.id.lv_fine_pointed_star, R.id.lv_circular_smile, R.id.lv_gears, R.id.lv_gears_two, R.id.lv_wifi, R.id.lv_circular_jump, R.id.lv_circular_zoom, R.id.lv_play_ball, R.id.lv_news, R.id.lv_line_text, R.id.lv_eat_beans, R.id.lv_chromeLogo, R.id.lv_ring_progress, R.id.lv_block, R.id.lv_funny_bar, R.id.lv_ghost, R.id.lv_blaze_wood, R.id.lv_battery, R.id.btn_start_all, R.id.btn_stop_all})
    public void onViewClicked(View view) {
        stopAll();
        switch (view.getId()) {
            case R.id.lv_circular_CD:
                lvCircularCD.startAnim();
                break;
            case R.id.lv_circular_ring:
                lvCircularRing.startAnim();
                break;
            case R.id.lv_circular:
                lvCircular.startAnim();
                break;
            case R.id.lv_fine_pointed_star:
                lvFinePointedStar.startAnim();
                break;
            case R.id.lv_circular_smile:
                lvCircularSmile.startAnim();
                break;
            case R.id.lv_gears:
                lvGears.startAnim();
                break;
            case R.id.lv_gears_two:
                lvGearsTwo.startAnim();
                break;
            case R.id.lv_wifi:
                lvWifi.startAnim();
                break;
            case R.id.lv_circular_jump:
                lvCircularJump.startAnim();
                break;
            case R.id.lv_circular_zoom:
                lvCircularZoom.startAnim();
                break;
            case R.id.lv_play_ball:
                lvPlayBall.startAnim();
                break;
            case R.id.lv_news:
                startLvNewsAnim();
                break;
            case R.id.lv_line_text:
                startLvLineWithTextAnim();
                break;
            case R.id.lv_eat_beans:
                lvEatBeans.startAnim();
                break;
            case R.id.lv_chromeLogo:
                lvChromeLogo.startAnim();
                break;
            case R.id.lv_ring_progress:
                lvRingProgress.startAnim();
                break;
            case R.id.lv_block:
                lvBlock.startAnim();
                break;
            case R.id.lv_funny_bar:
                lvFunnyBar.startAnim();
                break;
            case R.id.lv_ghost:
                lvGhost.startAnim();
                break;
            case R.id.lv_blaze_wood:
                lvBlazeWood.startAnim();
                break;
            case R.id.lv_battery:
                lvBattery.startAnim();
                break;
            case R.id.btn_start_all:
                stopAll();
                startAll();
                break;
            case R.id.btn_stop_all:
                stopAll();
                break;
        }
    }

    public void startAll() {
        lvCircular.startAnim();
        lvCircularRing.startAnim();
        lvPlayBall.startAnim();
        lvCircularJump.startAnim();
        lvCircularZoom.startAnim();
        startLvLineWithTextAnim();
        lvEatBeans.startAnim(3500);
        lvCircularCD.startAnim();
        lvCircularSmile.startAnim(1000);
        lvGears.startAnim();
        lvGearsTwo.startAnim();
        lvFinePointedStar.setDrawPath(true);
        lvFinePointedStar.startAnim(3500);
        lvChromeLogo.startAnim();
        lvBattery.startAnim(5000);
        lvWifi.startAnim(9000);
        startLvNewsAnim();
        lvBlock.startAnim();
        lvGhost.startAnim();
        lvFunnyBar.startAnim();
        lvRingProgress.startAnim(3000);
        lvBlazeWood.startAnim(500);
    }

    private void stopAll() {
        lvCircular.stopAnim();
        lvPlayBall.stopAnim();
        lvCircularJump.stopAnim();
        lvCircularZoom.stopAnim();
        lvCircularRing.stopAnim();
        lvEatBeans.stopAnim();
        stopLvLineWithTextAnim();
        lvCircularCD.stopAnim();
        lvCircularSmile.stopAnim();
        lvGears.stopAnim();
        lvGearsTwo.stopAnim();
        lvFinePointedStar.stopAnim();
        lvChromeLogo.stopAnim();
        lvBattery.stopAnim();
        lvWifi.stopAnim();
        stopLvNewsAnim();
        lvBlock.stopAnim();
        lvGhost.stopAnim();
        lvFunnyBar.stopAnim();
        lvRingProgress.stopAnim();
        lvBlazeWood.stopAnim();
    }

    private void startLvLineWithTextAnim() {
        mValueLVLineWithText = 0;
        if (mTimerLVLineWithText != null) {
            mTimerLVLineWithText.cancel();// 退出之前的mTimer
        }
        mTimerLVLineWithText = new Timer();
        timerTaskLvLineWithText();
    }

    private void stopLvLineWithTextAnim() {
        if (mTimerLVLineWithText != null) {
            mTimerLVLineWithText.cancel();// 退出之前的mTimer
            lvNews.setValue(mValueLVNews);
        }
    }

    private void startLvNewsAnim() {
        mValueLVNews = 0;
        if (mTimerLVNews != null) {
            mTimerLVNews.cancel();
        }
        mTimerLVNews = new Timer();
        timerTaskLvNews();
    }

    private void stopLvNewsAnim() {
        lvNews.stopAnim();
        if (mTimerLVNews != null) {
            mTimerLVNews.cancel();
            lvLineText.setValue(mValueLVLineWithText);
        }
    }


    public void timerTaskLvNews() {
        mTimerLVNews.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mValueLVNews < 100) {
                    mValueLVNews++;
                    Message msg = mHandler.obtainMessage(1);
                    msg.arg1 = mValueLVNews;
                    mHandler.sendMessage(msg);
                } else {
                    mTimerLVNews.cancel();
                }
            }
        }, 0, 10);
    }


    public void timerTaskLvLineWithText() {
        mTimerLVLineWithText.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mValueLVLineWithText < 100) {
                    mValueLVLineWithText++;
                    Message msg = mHandler.obtainMessage(2);
                    msg.arg1 = mValueLVLineWithText;
                    mHandler.sendMessage(msg);

                } else {
                    mTimerLVLineWithText.cancel();
                }
            }
        }, 0, 50);
    }

    @Override
    protected void onDestroy() {
        mTimerLVLineWithText.cancel();
        mTimerLVNews.cancel();
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
