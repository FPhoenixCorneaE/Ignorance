package com.github.ksoichiro.observablescrollview.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

public class ViewPagerTab2ActivityTest extends ActivityInstrumentationTestCase2<ViewPagerTab2Activity> {

    private ViewPagerTab2Activity activity;

    public ViewPagerTab2ActivityTest() {
        super(ViewPagerTab2Activity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        activity = getActivity();
    }

    public void testScroll() throws Throwable {
        for (int i = 0; i < 5; i++) {
            UiTestUtils.swipeHorizontally(this, activity.findViewById(R.id.pager), UiTestUtils.Direction.LEFT);
            getInstrumentation().waitForIdleSync();
            scroll();
        }
        for (int i = 0; i < 5; i++) {
            UiTestUtils.swipeHorizontally(this, activity.findViewById(R.id.pager), UiTestUtils.Direction.RIGHT);
            getInstrumentation().waitForIdleSync();
            scroll();
        }
    }

    public void scroll() throws Throwable {
        View scrollable = ((View) activity.getCurrentScrollable()).findViewById(R.id.scroll);

        UiTestUtils.swipeVertically(this, scrollable, UiTestUtils.Direction.UP);
        getInstrumentation().waitForIdleSync();

        UiTestUtils.swipeVertically(this, scrollable, UiTestUtils.Direction.DOWN);
        getInstrumentation().waitForIdleSync();
    }

    public void testSaveAndRestoreInstanceState() throws Throwable {
        for (int i = 0; i < 5; i++) {
            UiTestUtils.saveAndRestoreInstanceState(this, activity);
            scroll();

            UiTestUtils.swipeHorizontally(this, activity.findViewById(R.id.pager), UiTestUtils.Direction.LEFT);
            getInstrumentation().waitForIdleSync();
        }
    }
}
