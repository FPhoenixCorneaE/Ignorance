package io.vov.vitamio.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.R;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.utils.ScreenResolution;
import io.vov.vitamio.utils.StringUtils;
import io.vov.vitamio.widget.VideoView;

/**
 * 视频播放
 * Created by WangZhiYao on 2016/9/14.
 */
public class VideoPlayActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int UPDATE_PLAY_TIME = 0x01;//更新播放时间
    private static final int UPDATE_TIME = 800;
    private static final int HIDE_CONTROL_BAR = 0x02;//隐藏控制条
    private static final int HIDE_TIME = 5000;//隐藏控制条时间
    private static final int SHOW_CENTER_CONTROL = 0x03;//显示中间控制
    private static final int SHOW_CONTROL_TIME = 1000;
    private static final int UPDATE_DOWNLOAD_RATE = 0x04;//更新下载速率
    private static final int UPDATE_DOWNLOADED_PERCENT = 0x05;//更新下载进度

    private static final int ADD_FLAG = 1;
    private static final int SUB_FLAG = -1;
    private static final String VIDEO_URL = "video_url";//视频网络地址
    private static final String VIDEO_TITLE = "video_title";//视频网络标题


    private int mScreenWidth = 0;//屏幕宽度
    private boolean mIsFullScreen = false;//是否为全屏
    private long mVideoTotalTime = 0;//视频总时间
    private boolean mIntoSeek = false;//是否 快进/快退
    private long mSeek = 0;//快进的进度
    private boolean mIsFastFinish = false;

    private GestureDetector mGestureDetector;

    private AudioManager mAudioManager;
    private int mMaxVolume;//最大声音
    private int mShowVolume;//声音
    private int mShowLightness;//亮度
    private long playProgress;//播放进度


    private VideoView mVideoView;
    private ImageView mIvBack;
    /**
     * 视频标题
     */
    private TextView mTvVideoName;
    private RelativeLayout mControlTop;
    private ImageView mIvPlay;
    /**
     * 00:00/00:00
     */
    private TextView mTvTime;
    private SeekBar mSeekBar;
    private ImageView mIvIsFullscreen;
    private RelativeLayout mControlBottom;
    private ProgressBar mPbProgress;
    /**
     * 正在缓冲...
     */
    private TextView mTvBuffering;
    /**
     * 300kb/s
     */
    private TextView mTvDownloadRate;
    /**
     * 36%
     */
    private TextView mTvDownloadedPercent;
    private RelativeLayout mProgressBar;
    private ImageView mIvControlImg;
    /**
     * 12%
     */
    private TextView mTvControl;
    private LinearLayout mControlCenter;
    /**
     * 00:00/00:00
     */
    private TextView mTvFast;
    private FrameLayout mVideoLayout;
    /**
     * 重新播放
     */
    private Button mBtnReplay;
    private RelativeLayout mRlPlayCompletion;

    public void onClick(View view) {
        if (R.id.iv_back == view.getId()) {
            //返回键
            if (mIsFullScreen) {
                if (mVideoView.isPlaying()) {
                    mHandler.removeMessages(HIDE_CONTROL_BAR);
                    mHandler.sendEmptyMessageDelayed(HIDE_CONTROL_BAR, HIDE_TIME);
                }
                setupUnFullScreen();
            } else {
                finish();
            }
        } else if (R.id.iv_play == view.getId()) {
            //播放或者暂停
            if (mVideoView.isPlaying()) {
                mVideoView.pause();
                mIvPlay.setImageResource(R.mipmap.ic_video_play);
                mHandler.removeMessages(UPDATE_PLAY_TIME);
                mHandler.removeMessages(HIDE_CONTROL_BAR);
                showControlBar();
            } else {
                mVideoView.start();
                mIvPlay.setImageResource(R.mipmap.ic_video_pause);
                mHandler.sendEmptyMessage(UPDATE_PLAY_TIME);
                mHandler.sendEmptyMessageDelayed(HIDE_CONTROL_BAR, HIDE_TIME);
            }
        } else if (R.id.btn_replay == view.getId()) {
            //重新播放
            mRlPlayCompletion.setVisibility(View.GONE);
            replayVideo();
        } else if (R.id.iv_is_fullscreen == view.getId()) {
            //全屏或者退出全屏
            if (mIsFullScreen) {
                setupUnFullScreen();
            } else {
                setupFullScreen();
            }
            if (mVideoView.isPlaying()) {
                mHandler.removeMessages(HIDE_CONTROL_BAR);
                mHandler.sendEmptyMessageDelayed(HIDE_CONTROL_BAR, HIDE_TIME);
            }
        }
    }

    /**
     * 重新播放
     */
    private void replayVideo() {
        mTvTime.setText(sec2time(0) + "/" + sec2time(mVideoTotalTime));
        mVideoView.seekTo(0);
        mVideoView.start();
        mIvPlay.setImageResource(R.mipmap.ic_video_pause);
        mHandler.removeMessages(UPDATE_PLAY_TIME);
        mHandler.removeMessages(HIDE_CONTROL_BAR);
        mHandler.sendEmptyMessage(UPDATE_PLAY_TIME);
        mHandler.sendEmptyMessageDelayed(HIDE_CONTROL_BAR, HIDE_TIME);
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //更新下载速率
                case UPDATE_DOWNLOAD_RATE:
                    int extra = (int) msg.obj;
                    mTvDownloadRate.setText(String.format(Locale.getDefault(), "%dkb/s", extra));
                    break;
                //更新下载进度
                case UPDATE_DOWNLOADED_PERCENT:
                    int percent = (int) msg.obj;
                    mTvDownloadedPercent.setText(String.format(Locale.getDefault(), "%d%%", percent));
                    break;
                case UPDATE_PLAY_TIME:
                    long currentPosition = mVideoView.getCurrentPosition();
                    if (currentPosition <= mVideoTotalTime) {
                        //更新时间显示
                        mTvTime.setText(sec2time(currentPosition) + "/" + sec2time(mVideoTotalTime));
                        //更新进度条
                        int progress = (int) ((currentPosition * 1.0 / mVideoTotalTime) * 100);
                        mSeekBar.setProgress(progress);
                        mHandler.sendEmptyMessageDelayed(UPDATE_PLAY_TIME, UPDATE_TIME);
                    }
                    break;
                case HIDE_CONTROL_BAR:
                    hideControlBar();
                    break;
                case SHOW_CENTER_CONTROL:
                    mControlCenter.setVisibility(View.GONE);
                    break;
            }
        }
    };

    /**
     * 秒转化为常见格式
     */
    private String sec2time(long time) {
        return StringUtils.generateTime(time);
    }

    public static void startVideoPlay(Activity mActivity, String mVideoUrl, String mVideoTitle) {
        Intent intent = new Intent(mActivity, VideoPlayActivity.class);
        Bundle extras = new Bundle();
        extras.putString(VIDEO_URL, mVideoUrl);
        extras.putString(VIDEO_TITLE, mVideoTitle);
        intent.putExtras(extras);
        mActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(this);
        setContentView(R.layout.activity_video_play);

        initView();

        init();
    }

    private void init() {
        if (getIntent().getExtras() == null) {
            throw new NullPointerException("You should start VideoPlayActivity with its statical method.");
        }
        String mPlayUrl = getIntent().getExtras().getString(VIDEO_URL, "");
        String mVideoName = getIntent().getExtras().getString(VIDEO_TITLE, "");

        //获取屏幕宽度
        Pair<Integer, Integer> screenPair = ScreenResolution.getResolution(this);
        mScreenWidth = screenPair.first;
        //播放网络资源
        mVideoView.setVideoPath(mPlayUrl);
        //视频标题
        mTvVideoName.setText(mVideoName);
        //设置缓冲大小为2M
        mVideoView.setBufferSize(1024 * 1024 * 2);

        initVolumeWithLight();
        addVideoViewListener();
        addSeekBarListener();
        addTouchListener();
    }

    /**
     * 初始化声音和亮度
     */
    private void initVolumeWithLight() {
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mShowVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC) * 100 / mMaxVolume;
        mShowLightness = getScreenBrightness();
    }

    /**
     * 获得当前屏幕亮度值 0--255
     */
    private int getScreenBrightness() {
        int screenBrightness = 255;
        try {
            screenBrightness = Settings.System.getInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenBrightness;
    }

    /**
     * 为VideoView添加监听
     */
    private void addVideoViewListener() {
        //准备播放完成
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //获取播放总时长
                mVideoTotalTime = mVideoView.getDuration();
            }
        });

        //正在缓冲
        mVideoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                if (!mIntoSeek)
                    mProgressBar.setVisibility(View.VISIBLE);

                mHandler.removeMessages(UPDATE_PLAY_TIME);
                mHandler.removeMessages(HIDE_TIME);
                mHandler.removeMessages(UPDATE_DOWNLOADED_PERCENT);
                Message.obtain(mHandler, UPDATE_DOWNLOADED_PERCENT, percent).sendToTarget();
                mIvPlay.setImageResource(R.mipmap.ic_video_play);

                if (mVideoView.isPlaying())
                    mVideoView.pause();
            }
        });

        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    //缓冲开始
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        mTvDownloadRate.setText(String.format(Locale.getDefault(), "%dkb/s", 0));
                        mTvDownloadedPercent.setText(String.format(Locale.getDefault(), "%d%%", 0));
                        break;
                    //下载速率
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        mHandler.removeMessages(UPDATE_DOWNLOAD_RATE);
                        Message.obtain(mHandler, UPDATE_DOWNLOAD_RATE, extra).sendToTarget();
                        break;
                    //缓冲完成
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        mIvPlay.setImageResource(R.mipmap.ic_video_pause);
                        mHandler.removeMessages(UPDATE_PLAY_TIME);
                        mHandler.removeMessages(HIDE_CONTROL_BAR);
                        mHandler.sendEmptyMessage(UPDATE_PLAY_TIME);
                        mHandler.sendEmptyMessageDelayed(HIDE_CONTROL_BAR, HIDE_TIME);
                        mProgressBar.setVisibility(View.GONE);

                        if (!mVideoView.isPlaying())
                            mVideoView.start();
                        break;
                }

                return true;
            }
        });

        //视频播放出错
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if (what == MediaPlayer.MEDIA_ERROR_UNKNOWN) {
                    Toast.makeText(VideoPlayActivity.this, "该视频无法播放！", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

        //视频播放完成
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mRlPlayCompletion.setVisibility(View.VISIBLE);
                mHandler.removeMessages(UPDATE_PLAY_TIME);
                mHandler.removeMessages(HIDE_CONTROL_BAR);
            }
        });
    }

    /**
     * 为SeekBar添加监听
     */
    private void addSeekBarListener() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                long progress = (long) (seekBar.getProgress() * 1.0 / 100 * mVideoView.getDuration());
                mTvTime.setText(sec2time(progress) + "/" + sec2time(mVideoTotalTime));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mHandler.removeMessages(UPDATE_PLAY_TIME);
                mHandler.removeMessages(HIDE_CONTROL_BAR);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                long progress = (long) (seekBar.getProgress() * 1.0 / 100 * mVideoView.getDuration());
                mVideoView.seekTo(progress);
                mHandler.sendEmptyMessage(UPDATE_PLAY_TIME);

                //保存播放进度,按HOME键后返回恢复上次播放
                playProgress = progress;
            }
        });
    }

    /**
     * 添加手势操作
     */
    private void addTouchListener() {
        GestureDetector.SimpleOnGestureListener mSimpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
            //滑动操作
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                    float distanceX, float distanceY) {
                if (!mIsFullScreen)//非全屏不进行手势操作
                    return false;

                float x1 = e1.getX();
                float y1 = e1.getY();
                float x2 = e2.getX();
                float y2 = e2.getY();
                float absX = Math.abs(x1 - x2);
                float absY = Math.abs(y1 - y2);

                float absDistanceX = Math.abs(distanceX);// distanceX < 0 从左到右
                float absDistanceY = Math.abs(distanceY);// distanceY < 0 从上到下

                // Y方向的距离比X方向的大，即 上下 滑动
                if (absDistanceX < absDistanceY && !mIntoSeek) {
                    if (distanceY > 0) {//向上滑动
                        if (x1 >= mScreenWidth * 0.65) {//右边调节声音
                            changeVolume(ADD_FLAG);
                        } else {//调节亮度
                            changeLightness(ADD_FLAG);
                        }
                    } else {//向下滑动
                        if (x1 >= mScreenWidth * 0.65) {
                            changeVolume(SUB_FLAG);
                        } else {
                            changeLightness(SUB_FLAG);
                        }
                    }

                } else {// X方向的距离比Y方向的大，即 左右 滑动
                    if (absX > absY) {
                        mIntoSeek = true;
                        onSeekChange(x1, x2);
                        return true;
                    }
                }

                return false;
            }

            //双击事件，支持双击播放暂停，可从这实现
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                return super.onDoubleTap(e);
            }

            //单击事件
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {

                if (!mVideoView.isPlaying())
                    return false;

                if (mControlBottom.getVisibility() == View.VISIBLE) {
                    mHandler.removeMessages(HIDE_CONTROL_BAR);
                    hideControlBar();
                } else {
                    showControlBar();
                    mHandler.sendEmptyMessageDelayed(HIDE_CONTROL_BAR, HIDE_TIME);
                }

                return true;
            }
        };

        mGestureDetector = new GestureDetector(this, mSimpleOnGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector != null)
            mGestureDetector.onTouchEvent(event);

        if (event.getAction() == MotionEvent.ACTION_UP) {//手指抬起
            mTvFast.setVisibility(View.GONE);
            mIntoSeek = false;
            if (mIsFastFinish) {
                mVideoView.seekTo(mSeek);
                mIsFastFinish = false;
            }
        }

        return super.onTouchEvent(event);
    }

    /**
     * 左右滑动距离计算快进/快退时间
     */
    private void onSeekChange(float x1, float x2) {
        long currentPosition = mVideoView.getCurrentPosition();
        long seek;

        if (x1 - x2 > 200) {//向左滑
            if (currentPosition < 10000) {
                currentPosition = 0;
                seek = 0;
                setFlashText(seek);
                mVideoView.seekTo(currentPosition);
            } else {
                float distance = (x1 - x2);
                mVideoView.seekTo(currentPosition - (long) distance * 10);
                seek = currentPosition - (long) distance * 10;
                setFlashText(seek);
            }
        } else if (x2 - x1 > 200) { //向右滑动
            if (currentPosition + 10000 > mVideoView.getDuration()) {
                currentPosition = mVideoView.getDuration();
                mVideoView.seekTo(currentPosition);
                seek = currentPosition;
                setFlashText(seek);
            } else {
                float distance = x2 - x1;
                mVideoView.seekTo(currentPosition + (long) distance * 10);
                seek = currentPosition + (long) distance * 10;
                setFlashText(seek);
            }
        }

    }

    private void setFlashText(long seek) {
        String showTime = StringUtils.generateTime(seek) +
                "/" + StringUtils.generateTime(mVideoView.getDuration());
        mTvFast.setText(showTime);
        mSeek = seek;
        mIsFastFinish = true;

        if (mTvFast.getVisibility() != View.VISIBLE) {
            mTvFast.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 改变声音
     */
    private void changeVolume(int flag) {
        mShowVolume += flag;
        if (mShowVolume > 100) {
            mShowVolume = 100;
        } else if (mShowVolume < 0) {
            mShowVolume = 0;
        }
        mIvControlImg.setImageResource(R.mipmap.ic_volume);
        mTvControl.setText(String.format(Locale.getDefault(), "%d%%", mShowVolume));
        int tagVolume = mShowVolume * mMaxVolume / 100;
        //tagVolume:音量绝对值
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, tagVolume, 0);

        mHandler.removeMessages(SHOW_CENTER_CONTROL);
        mControlCenter.setVisibility(View.VISIBLE);
        mHandler.sendEmptyMessageDelayed(SHOW_CENTER_CONTROL, SHOW_CONTROL_TIME);
    }

    /**
     * 改变亮度
     */
    private void changeLightness(int flag) {
        mShowLightness += flag;
        if (mShowLightness > 255) {
            mShowLightness = 255;
        } else if (mShowLightness <= 0) {
            mShowLightness = 0;
        }
        mIvControlImg.setImageResource(R.mipmap.ic_lightness);
        mTvControl.setText(String.format(Locale.getDefault(), "%d%%", mShowLightness * 100 / 255));
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = mShowLightness / 255f;
        getWindow().setAttributes(lp);

        mHandler.removeMessages(SHOW_CENTER_CONTROL);
        mControlCenter.setVisibility(View.VISIBLE);
        mHandler.sendEmptyMessageDelayed(SHOW_CENTER_CONTROL, SHOW_CONTROL_TIME);
    }

    /**
     * 隐藏控制条
     */
    private void hideControlBar() {
        mControlBottom.setVisibility(View.GONE);
        mControlTop.setVisibility(View.GONE);
    }

    /**
     * 显示控制条
     */
    private void showControlBar() {
        mControlBottom.setVisibility(View.VISIBLE);
        mControlTop.setVisibility(View.VISIBLE);
    }

    /**
     * 设置为全屏
     */
    private void setupFullScreen() {
        //设置窗口模式
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //获取屏幕尺寸
        WindowManager manager = this.getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);

        //设置Video布局尺寸
        mVideoLayout.getLayoutParams().width = metrics.widthPixels;
        mVideoLayout.getLayoutParams().height = metrics.heightPixels;

        //设置为全屏拉伸
        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        mIvIsFullscreen.setImageResource(R.mipmap.ic_not_fullscreen);

        mIsFullScreen = true;
    }

    /**
     * 设置为非全屏
     */
    private void setupUnFullScreen() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setAttributes(attrs);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        int width = getResources().getDisplayMetrics().heightPixels;
        int height = dp2px(200.f);
        mVideoLayout.getLayoutParams().width = width;
        mVideoLayout.getLayoutParams().height = height;

        //设置为全屏
        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        mIvIsFullscreen.setImageResource(R.mipmap.ic_play_fullscreen);

        mIsFullScreen = false;
    }

    @Override
    public void onBackPressed() {
        if (mIsFullScreen) {
            setupUnFullScreen();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * dp转px
     */
    private int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (playProgress > 0) {
            //恢复上次播放进度
            restorePlay();
        }
    }

    /**
     * 恢复播放
     */
    private void restorePlay() {
        mTvTime.setText(sec2time(playProgress) + "/" + sec2time(mVideoTotalTime));
        mVideoView.seekTo(playProgress);
        mVideoView.start();
        mIvPlay.setImageResource(R.mipmap.ic_video_pause);
        showControlBar();
        mHandler.sendEmptyMessage(UPDATE_PLAY_TIME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //如果还在播放，则暂停
        if (mVideoView.isPlaying())
            mVideoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        mVideoView.stopPlayback();
    }

    private void initView() {
        mVideoView = (VideoView) findViewById(R.id.video_view);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
        mTvVideoName = (TextView) findViewById(R.id.tv_video_name);
        mControlTop = (RelativeLayout) findViewById(R.id.control_top);
        mIvPlay = (ImageView) findViewById(R.id.iv_play);
        mIvPlay.setOnClickListener(this);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        mIvIsFullscreen = (ImageView) findViewById(R.id.iv_is_fullscreen);
        mIvIsFullscreen.setOnClickListener(this);
        mControlBottom = (RelativeLayout) findViewById(R.id.control_bottom);
        mPbProgress = (ProgressBar) findViewById(R.id.pb_progress);
        mTvBuffering = (TextView) findViewById(R.id.tv_buffering);
        mTvDownloadRate = (TextView) findViewById(R.id.tv_download_rate);
        mTvDownloadedPercent = (TextView) findViewById(R.id.tv_downloaded_percent);
        mProgressBar = (RelativeLayout) findViewById(R.id.progress_bar);
        mIvControlImg = (ImageView) findViewById(R.id.iv_control_img);
        mTvControl = (TextView) findViewById(R.id.tv_control);
        mControlCenter = (LinearLayout) findViewById(R.id.control_center);
        mTvFast = (TextView) findViewById(R.id.tv_fast);
        mVideoLayout = (FrameLayout) findViewById(R.id.video_layout);
        mBtnReplay = (Button) findViewById(R.id.btn_replay);
        mBtnReplay.setOnClickListener(this);
        mRlPlayCompletion = (RelativeLayout) findViewById(R.id.rl_play_completion);
    }
}