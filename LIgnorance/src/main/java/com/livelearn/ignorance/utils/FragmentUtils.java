/*
 * Copyright (C) 2016 venshine.cn@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.livelearn.ignorance.utils;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Fragment操作
 *
 * @author venshine
 */
public class FragmentUtils {

    /**
     * Replace an existing fragment that was added to a container.
     */
    public static void replaceFragment(FragmentActivity activity, int containerViewId, Fragment newFragment, Bundle bundle,
                                       boolean canBack) {
        FragmentTransaction mFragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(containerViewId, newFragment, newFragment.getClass().getName());
        if (bundle != null) {
            newFragment.setArguments(bundle);
        }
        if (canBack) {
            mFragmentTransaction.addToBackStack(null);
        }
        mFragmentTransaction.commit();
    }

    /**
     * Add a fragment to the activity state. This fragment may optionally also have its view (if
     * {@link Fragment#onCreateView Fragment.onCreateView} returns non-null) into a container view of the activity.
     */
    public static void addFragment(FragmentActivity activity, int containerViewId, Fragment newFragment, Bundle bundle,
                                   boolean canBack) {
        FragmentTransaction mFragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        if (!newFragment.isAdded()) {
            mFragmentTransaction.add(containerViewId, newFragment, newFragment.getClass().getName());
        }
        if (bundle != null) {
            newFragment.setArguments(bundle);
        }
        if (canBack) {
            mFragmentTransaction.addToBackStack(null);
        }
        mFragmentTransaction.commit();
    }

    /**
     * Add a fragment to the fragment state. This fragment may optionally also have its view (if
     * {@link Fragment#onCreateView Fragment.onCreateView} returns non-null) into a container view of the fragment.
     */
    public static void addChildFragment(Fragment fragment, int containerViewId, Fragment newFragment, Bundle bundle,
                                        boolean canBack) {
        FragmentTransaction mFragmentTransaction = fragment.getChildFragmentManager().beginTransaction();
        if (!newFragment.isAdded()) {
            mFragmentTransaction.add(containerViewId, newFragment, newFragment.getClass().getName());
        }
        if (bundle != null) {
            newFragment.setArguments(bundle);
        }
        if (canBack) {
            mFragmentTransaction.addToBackStack(null);
        }
        mFragmentTransaction.commit();
    }

    /**
     * Hides an existing fragment. This is only relevant for fragments whose views have been added to a container, as
     * this will cause the view to be hidden.
     */
    public static void hideAndShowFragment(FragmentActivity activity, int containerViewId, Fragment previousFragment,
                                           Fragment newFragment, Bundle bundle, boolean canBack) {
        FragmentTransaction mFragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        if (bundle != null) {
            newFragment.setArguments(bundle);
        }
        if (newFragment.isAdded()) {
            if (null != previousFragment) {
                mFragmentTransaction.hide(previousFragment);
            }
            mFragmentTransaction.show(newFragment);
        } else {
            if (null != previousFragment) {
                mFragmentTransaction.hide(previousFragment);
            }
            mFragmentTransaction.add(containerViewId, newFragment, newFragment.getClass().getName());
            mFragmentTransaction.show(newFragment);
        }
        if (canBack && previousFragment != null) {
            mFragmentTransaction.addToBackStack(newFragment.getClass().getName());
        }
        mFragmentTransaction.commit();
    }

    public static void hideAndShowChildFragment(Fragment fragment, int containerViewId, Fragment previousFragment,
                                                Fragment newFragment, Bundle bundle, boolean canBack) {
        FragmentTransaction mFragmentTransaction = fragment.getChildFragmentManager().beginTransaction();
        if (bundle != null) {
            newFragment.setArguments(bundle);
        }
        if (newFragment.isAdded()) {
            if (null != previousFragment) {
                mFragmentTransaction.hide(previousFragment);
            }
            mFragmentTransaction.show(newFragment);
        } else {
            if (null != previousFragment) {
                mFragmentTransaction.hide(previousFragment);
            }
            mFragmentTransaction.add(containerViewId, newFragment, newFragment.getClass().getName());
            mFragmentTransaction.show(newFragment);
        }
        if (canBack && previousFragment != null) {
            mFragmentTransaction.addToBackStack(newFragment.getClass().getName());
        }
        mFragmentTransaction.commit();
    }

    /**
     * Remove an existing fragment. If it was added to a container, its view is also removed from that container.
     */
    public static void finishFragment(FragmentActivity activity, String... names) {
        FragmentManager manager = activity.getSupportFragmentManager();
        for (String name : names) {
            manager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        FragmentTransaction transaction = manager.beginTransaction();
        for (String name : names) {
            Fragment fragment = manager.findFragmentByTag(name);
            transaction.remove(fragment);
        }
        transaction.commit();
    }
}
