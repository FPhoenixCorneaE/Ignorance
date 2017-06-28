package com.livelearn.ignorance.test.smoothprogressbar;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import fr.castor.flex.circularprogressbar.CircularProgressBar;
import fr.castor.flex.circularprogressbar.CircularProgressDrawable;
import fr.castor.flex.smoothprogressbar.SmoothProgressBar;

public class TestCustomSmoothProgressBarActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.spb_smooth)
    SmoothProgressBar spbSmooth;

    @BindView(R.id.cpb_circular)
    CircularProgressBar cpbCircular;

    @BindView(R.id.cb_reversed)
    CheckBox cbReversed;

    @BindView(R.id.cb_mirror)
    CheckBox cbMirror;

    @BindView(R.id.cb_gradients)
    CheckBox cbGradients;

    @BindView(R.id.tv_speed)
    TextView tvSpeed;

    @BindView(R.id.sb_speed)
    SeekBar sbSpeed;

    @BindView(R.id.tv_stroke_width)
    TextView tvStrokeWidth;

    @BindView(R.id.sb_stroke_width)
    SeekBar sbStrokeWidth;

    @BindView(R.id.tv_separator_length)
    TextView tvSeparatorLength;

    @BindView(R.id.sb_separator_length)
    SeekBar sbSeparatorLength;

    @BindView(R.id.tv_sections_count)
    TextView tvSectionsCount;

    @BindView(R.id.sb_sections_count)
    SeekBar sbSectionsCount;

    @BindView(R.id.spn_interpolator)
    Spinner spnInterpolator;

    @BindView(R.id.tv_factor)
    TextView tvFactor;

    @BindView(R.id.sb_factor)
    SeekBar sbFactor;

    @BindView(R.id.btn_start)
    Button btnStart;

    @BindView(R.id.btn_stop)
    Button btnStop;

    private Interpolator mCurrentInterpolator;
    private int mStrokeWidth = 4;
    private int mSeparatorLength;
    private int mSectionsCount;
    private float mFactor = 1f;
    private float mSpeed = 1f;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_custom_smooth_progress_bar;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);
    }

    @Override
    public void setListeners() {
        sbFactor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mFactor = (progress + 1) / 10f;
                tvFactor.setText(String.format("Factor: %s", mFactor));
                setInterpolator(spnInterpolator.getSelectedItemPosition());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSpeed = ((float) progress + 1) / 10;
                tvSpeed.setText(String.format(Locale.getDefault(), "Speed: %s", mSpeed));
                spbSmooth.setSmoothProgressDrawableSpeed(mSpeed);
                spbSmooth.setSmoothProgressDrawableProgressiveStartSpeed(mSpeed);
                spbSmooth.setSmoothProgressDrawableProgressiveStopSpeed(mSpeed);
                updateValues();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbSectionsCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSectionsCount = progress + 1;
                tvSectionsCount.setText(String.format(Locale.getDefault(), "Sections count: %d", mSectionsCount));
                spbSmooth.setSmoothProgressDrawableSectionsCount(mSectionsCount);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbSeparatorLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSeparatorLength = progress;
                tvSeparatorLength.setText(String.format(Locale.getDefault(), "Separator length: %ddp", mSeparatorLength));
                spbSmooth.setSmoothProgressDrawableSeparatorLength(dpToPx(mSeparatorLength));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbStrokeWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mStrokeWidth = progress;
                tvStrokeWidth.setText(String.format(Locale.getDefault(), "Stroke width: %ddp", mStrokeWidth));
                spbSmooth.setSmoothProgressDrawableStrokeWidth(dpToPx(mStrokeWidth));
                updateValues();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        cbGradients.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spbSmooth.setSmoothProgressDrawableUseGradients(isChecked);
            }
        });

        cbMirror.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spbSmooth.setSmoothProgressDrawableMirrorMode(isChecked);
            }
        });

        cbReversed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spbSmooth.setSmoothProgressDrawableReversed(isChecked);
            }
        });

        sbSeparatorLength.setProgress(4);
        sbSectionsCount.setProgress(4);
        sbStrokeWidth.setProgress(4);
        sbSpeed.setProgress(9);
        sbFactor.setProgress(9);

        spnInterpolator.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.SmoothProgressBar_Interpolator)));
        spnInterpolator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setInterpolator(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnInterpolator.setSelection(4);
        updateValues();
    }

    private void setInterpolator(int position) {
        switch (position) {
            case 1:
                mCurrentInterpolator = new LinearInterpolator();
                sbFactor.setEnabled(false);
                break;
            case 2:
                mCurrentInterpolator = new AccelerateDecelerateInterpolator();
                sbFactor.setEnabled(false);
                break;
            case 3:
                mCurrentInterpolator = new DecelerateInterpolator(mFactor);
                sbFactor.setEnabled(true);
                break;
            case 4:
                mCurrentInterpolator = new FastOutSlowInInterpolator();
                sbFactor.setEnabled(true);
                break;
            case 0:
            default:
                mCurrentInterpolator = new AccelerateInterpolator(mFactor);
                sbFactor.setEnabled(true);
                break;
        }

        spbSmooth.setSmoothProgressDrawableInterpolator(mCurrentInterpolator);
        spbSmooth.setSmoothProgressDrawableColors(getResources().getIntArray(R.array.SmoothProgressBar_GPlus_Colors));
        updateValues();
    }

    private void updateValues() {
        CircularProgressDrawable circularProgressDrawable;
        CircularProgressDrawable.Builder b = new CircularProgressDrawable.Builder(this)
                .colors(getResources().getIntArray(R.array.SmoothProgressBar_GPlus_Colors))
                .sweepSpeed(mSpeed)
                .rotationSpeed(mSpeed)
                .strokeWidth(dpToPx(mStrokeWidth))
                .style(CircularProgressDrawable.STYLE_ROUNDED);
        if (mCurrentInterpolator != null) {
            b.sweepInterpolator(mCurrentInterpolator);
        }
        cpbCircular.setIndeterminateDrawable(circularProgressDrawable = b.build());

        // /!\ Terrible hack, do not do this at home!
        circularProgressDrawable.setBounds(0,
                0,
                cpbCircular.getWidth(),
                cpbCircular.getHeight());
        cpbCircular.setVisibility(View.INVISIBLE);
        cpbCircular.setVisibility(View.VISIBLE);
    }

    public int dpToPx(int dp) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    @OnClick({R.id.btn_start, R.id.btn_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                spbSmooth.progressiveStart();
                cpbCircular.progressiveStart();
                break;
            case R.id.btn_stop:
                spbSmooth.progressiveStop();
                cpbCircular.progressiveStop();
                break;
        }
    }
}
