-keepattributes Exceptions,InnerClasses,...
-keep class com.davemorrissey.labs.subscaleview.** { *; }
#所有SubsamplingScaleImageView的内部类都保留下来
-keep class com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView$* {
    *;
}