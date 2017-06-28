package com.maiml.library.factory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.maiml.library.item.ArrowItem;
import com.maiml.library.item.RMSwitchItem;
import com.maiml.library.item.SwitchItem;
import com.maiml.library.item.NormalItem;
import com.maiml.library.item.RedTextItem;
import com.maiml.library.item.TextItem;
import com.maiml.library.config.ConfigAttrs;
import com.maiml.library.item.AbstractItem;
import com.maiml.library.config.Mode;

/**
 * Created by maimingliang on 2016/12/4.
 */

public class ItemFactory extends AbstractItemFactory {

    public ItemFactory(Context context) {
        super(context);
    }

    /**
     * 创建出ItemView
     *
     * @param mode  通过传入 不同mode 来创建出不同类型的itemview
     * @param attrs
     * @param <T>
     * @return
     */
    @Override
    public <T extends AbstractItem> T createItem(@NonNull Mode mode, ConfigAttrs attrs, int index) {
        Log.e("mode", mode.name());

        AbstractItem item = null;

        try {

            if (mode == Mode.NORMAL) {
                item = new NormalItem(mContext);
            } else if (mode == Mode.ARROW) {
                item = new ArrowItem(mContext);
            } else if (mode == Mode.SWITCH) {
                item = new SwitchItem(mContext);
            } else if (mode == Mode.TEXT) {
                item = new TextItem(mContext);
            } else if (mode == Mode.RED_TEXT) {
                item = new RedTextItem(mContext);
            } else if (mode == Mode.RMSWITCH) {
                item = new RMSwitchItem(mContext);
            }


            if (item != null) {
                item.create(attrs, index);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }


        return (T) item;
    }
}
