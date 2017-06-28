package com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder;

/**
 * A interface that defines what a View Holder Creator should do.
 *
 * @param <ItemDataType> the generic type of the data in each item of a list.
 * @author http://www.liaohuqiu.net
 */
public interface ViewHolderCreator<ItemDataType> {
    ViewHolderBase<ItemDataType> createViewHolder();
}
