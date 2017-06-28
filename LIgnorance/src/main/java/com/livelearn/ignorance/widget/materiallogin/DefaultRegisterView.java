package com.livelearn.ignorance.widget.materiallogin;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.livelearn.ignorance.R;

public class DefaultRegisterView extends FrameLayout implements RegisterView {

    private SimpleDraweeView iVAvatar;

    public interface DefaultRegisterViewListener {
        void onRegister(TextInputLayout registerUser, TextInputLayout registerNickname, TextInputLayout registerPass, TextInputLayout registerPassRep);
    }

    private DefaultRegisterViewListener listener;
    private View registerCancel;


    public DefaultRegisterView(Context context) {
        this(context, null);
    }

    public DefaultRegisterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefaultRegisterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DefaultRegisterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_register, this, true);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DefaultRegisterView, 0, 0);

        TextView registerTitle = (TextView) findViewById(R.id.register_title);
        iVAvatar = (SimpleDraweeView) findViewById(R.id.iv_avatar);
        final TextInputLayout registerUser = (TextInputLayout) findViewById(R.id.register_user);
        final TextInputLayout registerNickname = (TextInputLayout) findViewById(R.id.register_nickname);
        final TextInputLayout registerPass = (TextInputLayout) findViewById(R.id.register_pass);
        final TextInputLayout registerPassRep = (TextInputLayout) findViewById(R.id.register_pass_rep);
        TextView registerBtn = (TextView) findViewById(R.id.register_btn);
        registerCancel = findViewById(R.id.register_cancel);


        registerBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRegister(registerUser, registerNickname, registerPass, registerPassRep);
            }
        });

        try {
            String string = a.getString(R.styleable.DefaultRegisterView_registerTitle);
            if (string != null) {
                registerTitle.setText(string);
            }

            string = a.getString(R.styleable.DefaultRegisterView_registerHint);
            if (string != null) {
                registerUser.setHint(string);
            }

            string = a.getString(R.styleable.DefaultRegisterView_registerNicknameHint);
            if (string != null) {
                registerNickname.setHint(string);
            }

            string = a.getString(R.styleable.DefaultRegisterView_registerPasswordHint);
            if (string != null) {
                registerPass.setHint(string);
            }

            string = a.getString(R.styleable.DefaultRegisterView_registerRepeatPasswordHint);
            if (string != null) {
                registerPassRep.setHint(string);
            }

            string = a.getString(R.styleable.DefaultRegisterView_registerActionText);
            if (string != null) {
                registerBtn.setText(string);
            }

            int color = a.getColor(R.styleable.DefaultRegisterView_registerTextColor, ContextCompat.getColor(getContext(), R.color.material_login_login_register_text_color));
            if (registerUser.getEditText() != null) {
                registerUser.getEditText().setTextColor(color);
            }
            if (registerNickname.getEditText() != null) {
                registerNickname.getEditText().setTextColor(color);
            }
            if (registerPass.getEditText() != null) {
                registerPass.getEditText().setTextColor(color);
            }
            if (registerPassRep.getEditText() != null) {
                registerPassRep.getEditText().setTextColor(color);
            }
        } finally {
            a.recycle();
        }
    }

    public void setListener(DefaultRegisterViewListener listener) {
        this.listener = listener;
    }

    @Override
    public View getCancelRegisterView() {
        return registerCancel;
    }

    public SimpleDraweeView getIVAvatar() {
        return iVAvatar;
    }
}
