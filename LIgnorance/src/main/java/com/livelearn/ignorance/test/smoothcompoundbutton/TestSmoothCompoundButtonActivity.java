package com.livelearn.ignorance.test.smoothcompoundbutton;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;
import com.livelearn.ignorance.widget.smoothcompoundbutton.SmoothCompoundButton;

import java.io.File;
import java.io.FileOutputStream;


public class TestSmoothCompoundButtonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_smooth_compound_button;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        final int[] ctrlIds = new int[]{R.id.sample_smoothswitch1,
                R.id.sample_smoothcheckbox1,
                R.id.sample_smoothcheckbox2,
                R.id.sample_smoothradiobutton1,
                R.id.sample_smoothradiobutton2
        };
        ((CheckBox) findViewById(R.id.ctrl_enabled)).setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (int id : ctrlIds) {
                    findViewById(id).setEnabled(isChecked);
                }
            }
        });
        ((CheckBox) findViewById(R.id.ctrl_checked_noanimation)).setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (int id : ctrlIds) {
                    ((SmoothCompoundButton) findViewById(id)).setChecked(isChecked, false, true);
                }
            }
        });

        ((CheckBox) findViewById(R.id.ctrl_checked_withanimation)).setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (int id : ctrlIds) {
                    ((SmoothCompoundButton) findViewById(id)).setChecked(isChecked);
                }
            }
        });
    }

    @Override
    public void setListeners() {

    }

    public void onClickAppCompatImageView(View v) {
        Toast.makeText(getApplicationContext(), "Oh, I am just a image copied from AppCompatLibrary.", Toast.LENGTH_SHORT).show();
    }

    public void saveSamplePicture(View v) {
        //Delay是为了让这个按钮的pressed的状态消失
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                LinearLayout layout = (LinearLayout) findViewById(R.id.main_linearlayout);
                final String fileName = "SmoothCompoundButton_Sample_" + System.currentTimeMillis() + ".png";
                String filePath = Environment.getExternalStorageDirectory() + File.separator + fileName;
                if (saveViewAsBitmapFile(layout, 0xffffffff, filePath)) {
                    Toast.makeText(getApplicationContext(), "Save OK\n" + fileName, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Save Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, 200);

    }

    public static boolean saveViewAsBitmapFile(View view, int backgroundColor, String filePath) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(backgroundColor);
        view.draw(canvas);
        File file = new File(filePath);
        try {
            FileOutputStream out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                out.flush();
                out.close();
                return true;
            }
        } catch (OutOfMemoryError | Exception error) {
            error.printStackTrace();
        }
        return false;
    }
}
