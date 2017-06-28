package com.livelearn.ignorance.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.widget.cbprogressBar.CBProgressBar;
import com.livelearn.ignorance.widget.jumpingbeans.JumpingBeans;
import com.zhl.cbdialog.titanic.Titanic;
import com.zhl.cbdialog.titanic.TitanicTextView;

public class DialogUtils {

    /**
     * 创建加载Dialog
     *
     * @param context 上下文
     * @param message 文字
     */
    public static Dialog createLoadingDialog(Context context, String message, boolean appendJumpingDots) {
        Dialog dialog = new Dialog(context, R.style.DialogTheme_JumpingBeans_Loading);
        dialog.setContentView(R.layout.dialog_jumping_beans_loading);
        dialog.setCancelable(true);
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView tvMessage = (TextView) dialog.findViewById(R.id.tv_message);
        tvMessage.setText(message);

        if (appendJumpingDots) {
            JumpingBeans.with(tvMessage)
                    .appendJumpingDots()
                    .build();
        }
        return dialog;
    }

    /**
     * 得到图片加载中Dialog
     *
     * @param loadingText 图片加载文本
     */
    public static Dialog createLoadingIndicatorDialog(Context context, boolean showProgressBar, String loadingText) {
        Dialog loadingDialog = new Dialog(context, R.style.DialogTheme_Transparent);
        loadingDialog.setContentView(R.layout.dialog_loading_indicator);
        loadingDialog.setCancelable(false);

        CBProgressBar cbProgressBar = (CBProgressBar) loadingDialog.findViewById(R.id.cbpb_loading);
        if (showProgressBar)
            cbProgressBar.setMax(100);
        else
            cbProgressBar.setVisibility(View.GONE);

        TitanicTextView tvLoadingText = (TitanicTextView) loadingDialog.findViewById(R.id.tv_loading_text);
        tvLoadingText.setText(loadingText);
        new Titanic().start(tvLoadingText);
        return loadingDialog;
    }
}
