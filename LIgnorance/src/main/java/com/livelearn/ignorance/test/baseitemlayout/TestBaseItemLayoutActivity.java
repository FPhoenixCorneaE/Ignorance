package com.livelearn.ignorance.test.baseitemlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.maiml.library.BaseItemLayout;
import com.maiml.library.config.ConfigAttrs;
import com.maiml.library.config.Mode;
import com.maiml.library.rmswitch.RMSwitch;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TestBaseItemLayoutActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.base_item_layout)
    BaseItemLayout baseItemLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_base_item_layout;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {

        tbTitle.setTitleText(className);


        final List<String> valueList = new ArrayList<>();

        valueList.add("相册");
        valueList.add("收藏");
        valueList.add("钱包");
        valueList.add("卡包");
        valueList.add("设置");
        valueList.add("子项只有左边");
        valueList.add("左边没有图标");
        valueList.add("rmswitch Toggle上增加图标");
        valueList.add("rmswitch自定义颜色");
        valueList.add("rmswitch不允许操作");
        valueList.add("默认rmswitch样式");

        List<Integer> resIdList = new ArrayList<>();

        resIdList.add(R.mipmap.ic_setting_xc);
        resIdList.add(R.mipmap.ic_setting_sc);
        resIdList.add(R.mipmap.ic_setting_qb);
        resIdList.add(R.mipmap.ic_setting_kb);
        resIdList.add(R.mipmap.ic_setting_sz);
        resIdList.add(R.mipmap.ic_setting_sz);

        ConfigAttrs attrs = new ConfigAttrs(); // 把全部参数的配置，委托给ConfigAttrs类处理。

        //参数 使用链式方式配置
        attrs.setValueList(valueList)  // 文字 list
                .setResIdList(resIdList) // icon list
                .setIconWidth(24)  //设置icon 的大小，默认为24
                .setIconHeight(24)
                .setItemMarginTop(1, 20)// 设置 position 下的item 的 间距,默认为10
                .setItemMode(0, Mode.TEXT)
                .setItemMode(1, Mode.TEXT) //设置第二个item 为 Mode.TEXT 模式，默认为Mode.NORMAL 模式
                .setItemMode(2, Mode.ARROW)//设置第三个item 为 Mode.ARROW 模式
                .setItemMode(3, Mode.SWITCH)//设置第四个item 为 Mode.SWITCH 模式
                .setItemMode(4, Mode.RED_TEXT)//设置第五个item 为 Mode.RED_TEXT 模式
                .setItemMode(7, Mode.RMSWITCH)
                .setItemMode(8, Mode.RMSWITCH)
                .setItemMode(9, Mode.RMSWITCH)
                .setItemMode(10, Mode.RMSWITCH)
                .setRightText(0, "text1")
                .setRightText(1, "text2") // 设置第二个Item 的值，Mode.TEXT 模式下必须设置值
                .setRightText(4, "22") //设置第五个item  Mode.RED_TEXT 模式下必须设置值并且为数字
                .setArrowResId(R.mipmap.ic_next) //设置箭头资源值,没设置有默认资源值
                .setArrowIsShow(0, false)//设置某一项的箭头不显示，默认为true
                .setUpResId(R.drawable.img_up) //设置打开按钮资源值,没设置有默认资源值
                .setTrunResId(R.drawable.img_turn_down)//设置关闭按钮资源值,没设置有默认资源值
                .setRmSwitchForceAspectRatio(7, false)//宽高比例不固定，默认使用固定比例
                .setRmSwitchWidth(60)
                .setRmSwitchHeight(35)//宽高比例不固定时，该值才有效
                .setRmSwitchCheckedBgColor(8, Color.BLUE)
                .setRmSwitchNotCheckedBgColor(8, Color.BLACK)
                .setRmSwitchCheckedToggleColor(8, Color.WHITE)
                .setRmSwitchNotCheckedToggleColor(8, Color.YELLOW)
                .setRmSwitchCheckedToggleRes(7, R.drawable.img_up)
                .setRmSwitchNotCheckedToggleRes(7, R.drawable.img_turn_down)
                .setRmSwitchEnabled(9, false)
                .setItemMarginRight(0, 20)//设置 position 距右边边距，默认为10
                .setItemMarginRight(3, 20)//设置 position 距右边边距
                .setItemMarginRight(7, 20)//设置 position 距右边边距
                .setItemMarginRight(8, 20)//设置 position 距右边边距
                .setItemMarginRight(9, 20)//设置 position 距右边边距
                .setItemMarginRight(10, 20)//设置 position 距右边边距
                .setShowStartLine(false)//默认是false
                .setShowEndLine(true)//默认是true
        ;

        baseItemLayout.setConfigAttrs(attrs).create(); //

        baseItemLayout.setOnBaseItemClick(1, new BaseItemLayout.OnBaseItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showToast("点击了" + valueList.get(position));
            }
        });

        baseItemLayout.setOnBaseItemClick(new int[]{2, 4}, new BaseItemLayout.OnBaseItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showToast("点击了" + valueList.get(position));
            }
        });

        baseItemLayout.setOnSwitchClickListener(new BaseItemLayout.OnSwitchClickListener() {
            @Override
            public void onClick(int position, boolean isChecked) {
                ToastUtils.showToast((isChecked ? "打开了" : "关闭了") + valueList.get(position));
            }
        });

        baseItemLayout.addOnRMSwitchObserver(new BaseItemLayout.OnRMSwitchObserver() {
            @Override
            public void onCheckedStateChange(RMSwitch switchView, boolean isChecked, int position) {
                ToastUtils.showToast((isChecked ? "打开了" : "关闭了") + valueList.get(position));
            }
        });
    }

    @Override
    public void setListeners() {

    }
}
