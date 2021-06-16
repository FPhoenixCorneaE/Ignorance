package com.livelearn.ignorance.widget.materiallogin;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import com.google.android.material.textfield.TextInputLayout;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.widget.jellytogglebutton.JellyToggleButton;

import am.widget.shapeimageview.ShapeImageView;

public class DefaultLoginView extends FrameLayout {

    private ShapeImageView iVAvatar;
    private TextInputLayout loginUser;
    private TextInputLayout loginPass;
    private JellyToggleButton jtbRemember;
    private JellyToggleButton jtbAutoLogin;

    public interface DefaultLoginViewListener {
        void onLogin(TextInputLayout loginUser, TextInputLayout loginPass);
    }

    private DefaultLoginViewListener listener;


    public DefaultLoginView(Context context) {
        this(context, null);
    }

    public DefaultLoginView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefaultLoginView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DefaultLoginView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_login, this, true);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DefaultLoginView, 0, 0);

        TextView loginTitle = (TextView) findViewById(R.id.login_title);
        iVAvatar = (ShapeImageView) findViewById(R.id.iv_avatar);
        loginUser = (TextInputLayout) findViewById(R.id.login_user);
        loginPass = (TextInputLayout) findViewById(R.id.login_pass);
        jtbRemember = (JellyToggleButton) findViewById(R.id.jtb_remember);
        jtbAutoLogin = (JellyToggleButton) findViewById(R.id.jtb_auto_login);
        TextView loginBtn = (TextView) findViewById(R.id.login_btn);

        findViewById(R.id.login_btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLogin(loginUser, loginPass);
                }
            }
        });
        try {
            String string = a.getString(R.styleable.DefaultLoginView_loginTitle);
            if (string != null) {
                loginTitle.setText(string);
            }

            string = a.getString(R.styleable.DefaultLoginView_loginHint);
            if (string != null) {
                loginUser.setHint(string);
            }

            string = a.getString(R.styleable.DefaultLoginView_loginPasswordHint);
            if (string != null) {
                loginPass.setHint(string);
            }

            string = a.getString(R.styleable.DefaultLoginView_loginActionText);
            if (string != null) {
                loginBtn.setText(string);
            }

            int color = a.getColor(R.styleable.DefaultLoginView_loginTextColor, ContextCompat.getColor(getContext(), R.color.material_login_login_register_text_color));
            if (loginUser.getEditText() != null) {
                loginUser.getEditText().setTextColor(color);
            }
            if (loginPass.getEditText() != null) {
                loginPass.getEditText().setTextColor(color);
            }
        } finally {
            a.recycle();
        }
    }

    public void setListener(DefaultLoginViewListener listener) {
        this.listener = listener;
    }

    public ShapeImageView getIvAvatar() {
        return iVAvatar;
    }

    public TextInputLayout getLoginPass() {
        return loginPass;
    }

    public TextInputLayout getLoginUser() {
        return loginUser;
    }

    public JellyToggleButton getJtbRemember() {
        return jtbRemember;
    }

    public JellyToggleButton getJtbAutoLogin() {
        return jtbAutoLogin;
    }
}
