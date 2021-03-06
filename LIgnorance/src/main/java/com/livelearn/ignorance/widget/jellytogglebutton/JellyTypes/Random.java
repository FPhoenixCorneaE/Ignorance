package com.livelearn.ignorance.widget.jellytogglebutton.JellyTypes;


import com.livelearn.ignorance.widget.jellytogglebutton.EaseTypes.EaseType;
import com.livelearn.ignorance.widget.jellytogglebutton.PointWithHorizontalPoints;
import com.livelearn.ignorance.widget.jellytogglebutton.PointWithVerticalPoints;
import com.livelearn.ignorance.widget.jellytogglebutton.State;

/**
 * Created by Weiping on 2016/5/11.
 */

public class Random extends JellyStyle {

    @Override
    public void changeShape(PointWithHorizontalPoints p1, PointWithVerticalPoints p2, PointWithHorizontalPoints p3, PointWithVerticalPoints p4, float stretchDistance, float bezierControlValue, float bezierScaleRatioValue, float thumbRadius, float process, State state) {
        // random
    }

    @Override
    public void changeOffset(PointWithHorizontalPoints p1, PointWithVerticalPoints p2, PointWithHorizontalPoints p3, PointWithVerticalPoints p4, float totalLength, float extractLength, float process, State state, EaseType easeType) {
        // the random value r has been set
    }

    @Override
    public float extractLength(float stretchDistance, float bezierControlValue, float bezierScaleRatioValue, float thumbRadius) {
        return 0;
    }
}
