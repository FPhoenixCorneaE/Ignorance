package com.hitomi.tilibrary.transfer;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.hitomi.tilibrary.R;

/**
 * Created on 2017/6/20.
 */

public class TransDialog extends Dialog implements DialogInterface.OnShowListener, DialogInterface.OnKeyListener, TransferLayout.OnLayoutResetListener {

    private TransferLayout transferLayout;
    private OnTransfereeStateChangeListener transListener;

    // 因为Dialog的关闭有动画延迟，固不能使用 dialog.isShowing, 去判断 transferee 的显示逻辑
    private boolean shown;

    TransDialog(@NonNull Context context) {
        super(context, R.style.DialogTheme_Translucent_NoTitleBar_Fullscreen);

        transferLayout = new TransferLayout(context);
        transferLayout.setOnLayoutResetListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(transferLayout);

        setOnShowListener(this);
        setOnKeyListener(this);
    }

    /**
     * 配置 transferee 参数对象
     *
     * @param config 参数对象
     */
    void applyTransferee(TransferConfig config) {
        transferLayout.apply(config);
    }

    /**
     * 显示 transferee
     */
    void showTransferee() {
        if (shown) return;
        this.show();
        if (transListener != null)
            transListener.onShow();

        shown = true;
    }

    /**
     * 显示 transferee, 并设置 OnTransfereeChangeListener
     *
     * @param listener {@link OnTransfereeStateChangeListener}
     */
    void showTransferee(OnTransfereeStateChangeListener listener) {
        if (shown) return;
        this.show();
        transListener = listener;
        transListener.onShow();

        shown = true;
    }

    /**
     * 关闭 transferee
     */
    void dismissTransferee(TransferConfig transferConfig) {
        if (!shown) return;
        transferLayout.dismiss(transferConfig.getNowThumbnailIndex());
        shown = false;
    }

    /**
     * transferee 是否显示
     *
     * @return true ：显示, false ：关闭
     */
    boolean isShown() {
        return shown;
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP && !event.isCanceled()) {
            dismiss();
        }
        return true;
    }

    @Override
    public void onShow(DialogInterface dialog) {
        transferLayout.show();
    }

    @Override
    public void onReset() {
        dismiss();
        if (transListener != null)
            transListener.onDismiss();

        shown = false;
    }

    /**
     * 设置 Transferee 显示和关闭的监听器
     * <p>
     * Transferee 显示的时候调用 {@link OnTransfereeStateChangeListener#onShow()}
     * <p>
     * Transferee 关闭的时候调用 {@link OnTransfereeStateChangeListener#onDismiss()}
     */
    public interface OnTransfereeStateChangeListener {

        void onShow();

        void onDismiss();
    }
}
