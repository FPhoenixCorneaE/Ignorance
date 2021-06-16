package me.iwf.photopicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import cn.finalteam.toolsfinal.DateUtils;
import cn.finalteam.toolsfinal.DeviceUtils;
import cn.finalteam.toolsfinal.StringUtils;
import cn.finalteam.toolsfinal.io.FileUtils;

/**
 * Created by Donglua on 16/6/25.
 * Builder class to ease Intent setup.
 */
public class PhotoPicker {

    public static final int REQUEST_CODE = 69;
    public static final int TAKE_PHOTO_REQUEST_CODE = 66;

    public final static int DEFAULT_MAX_COUNT = 9;
    public final static int DEFAULT_COLUMN_NUMBER = 3;

    public final static String KEY_SELECTED_PHOTOS = "SELECTED_PHOTOS";

    public final static String EXTRA_MAX_COUNT = "MAX_COUNT";
    public final static String EXTRA_SHOW_CAMERA = "SHOW_CAMERA";
    public final static String EXTRA_SHOW_GIF = "SHOW_GIF";
    public final static String EXTRA_GRID_COLUMN = "column";
    public final static String EXTRA_ORIGINAL_PHOTOS = "ORIGINAL_PHOTOS";
    public final static String EXTRA_PREVIEW_ENABLED = "PREVIEW_ENABLED";
    public static final String EXTRA_ISCROPED = "EXTRA_ISCROPED";

    public static Uri mTakePhotoUri;

    public static PhotoPickerBuilder builder() {
        return new PhotoPickerBuilder();
    }

    public static class PhotoPickerBuilder {
        private Bundle mPickerOptionsBundle;
        private Intent mPickerIntent;
        private static String mPhotoTargetFolder;

        public PhotoPickerBuilder() {
            mPickerOptionsBundle = new Bundle();
            mPickerIntent = new Intent();
        }

        /**
         * Send the Intent from an Activity with a custom request code
         *
         * @param activity    Activity to receive result
         * @param requestCode requestCode for result
         */
        public void start(@NonNull Activity activity, int requestCode) {
            activity.startActivityForResult(getIntent(activity), requestCode);
        }

        /**
         * Send the Intent from an Activity with a custom request code
         *
         * @param activity Activity to receive result
         */
        void startCamera(@NonNull Activity activity) {
            if (!DeviceUtils.existSDCard()) {
                Toast.makeText(activity, R.string.__picker_empty_sdcard, Toast.LENGTH_SHORT).show();
                return;
            }
            if (StringUtils.isEmpty(mPhotoTargetFolder)) {
                mPhotoTargetFolder = Environment.getExternalStorageDirectory() + "/DCIM/" + "PhotoPicker/";
            }
            File takePhotoFolder = new File(mPhotoTargetFolder);

            boolean suc = FileUtils.mkdirs(takePhotoFolder);
            File toFile = new File(takePhotoFolder, "IMG" + DateUtils.format(new Date(), "yyyyMMddHHmmss") + ".jpg");
            mTakePhotoUri = Uri.fromFile(toFile);
            if (suc) {
                activity.startActivityForResult(getCaptureIntent(mTakePhotoUri), TAKE_PHOTO_REQUEST_CODE);
            }
        }

        /**
         * Send the Intent with a custom request code
         *
         * @param fragment    Fragment to receive result
         * @param requestCode requestCode for result
         */
        public void start(@NonNull Context context, @NonNull androidx.fragment.app.Fragment fragment, int requestCode) {
            fragment.startActivityForResult(getIntent(context), requestCode);
        }

        /**
         * Send the Intent with a custom request code
         *
         * @param fragment Fragment to receive result
         */
        public void start(@NonNull Context context, @NonNull androidx.fragment.app.Fragment fragment) {
            fragment.startActivityForResult(getIntent(context), REQUEST_CODE);
        }

        /**
         * Get Intent to start {@link PhotoPickerActivity}
         *
         * @return Intent for {@link PhotoPickerActivity}
         */
        Intent getIntent(@NonNull Context context) {
            mPickerIntent.setClass(context, PhotoPickerActivity.class);
            mPickerIntent.putExtras(mPickerOptionsBundle);
            return mPickerIntent;
        }

        /**
         * Get CaptureIntent to start
         *
         * @return Intent for camera
         */
        Intent getCaptureIntent(Uri mTakePhotoUri) {
            mPickerIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            mPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, mTakePhotoUri);
            return mPickerIntent;
        }

        /**
         * Send the crop Intent from an Activity
         *
         * @param activity Activity to receive result
         */
        public void start(@NonNull Activity activity) {
            start(activity, REQUEST_CODE);
        }

        public PhotoPickerBuilder setPhotoCount(int photoCount) {
            mPickerOptionsBundle.putInt(EXTRA_MAX_COUNT, photoCount);
            return this;
        }

        public PhotoPickerBuilder setGridColumnCount(int columnCount) {
            mPickerOptionsBundle.putInt(EXTRA_GRID_COLUMN, columnCount);
            return this;
        }

        PhotoPickerBuilder setShowGif(boolean showGif) {
            mPickerOptionsBundle.putBoolean(EXTRA_SHOW_GIF, showGif);
            return this;
        }

        public PhotoPickerBuilder setShowCamera(boolean showCamera) {
            mPickerOptionsBundle.putBoolean(EXTRA_SHOW_CAMERA, showCamera);
            return this;
        }

        PhotoPickerBuilder setSelected(ArrayList<String> imagesUri) {
            mPickerOptionsBundle.putStringArrayList(EXTRA_ORIGINAL_PHOTOS, imagesUri);
            return this;
        }

        PhotoPickerBuilder setPreviewEnabled(boolean previewEnabled) {
            mPickerOptionsBundle.putBoolean(EXTRA_PREVIEW_ENABLED, previewEnabled);
            return this;
        }

        PhotoPickerBuilder setCrop(boolean isCroped) {
            mPickerOptionsBundle.putBoolean(EXTRA_ISCROPED, isCroped);
            return this;
        }
    }
}
