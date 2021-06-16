package me.iwf.photopicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.ColorInt;
import android.util.Log;

import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.yalantis.ucrop.util.StatusBarCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;
import static me.iwf.photopicker.PhotoPicker.mTakePhotoUri;

public class PhotoPickUtils {

    private static final int REQUEST_CAMERA = 66;
    private static final String EXTRA_VIEW_TAG = "viewTag";//同一个页面多个地方需要选择图片时，config里tag字段用于标识

    public static final int TYPE_AVATAR = 1;
    public static final int TYPE_NORMAL = 2;

    public static Uri getUri() {
        return uri;
    }

    private static Uri uri;

    private static CropConfig config = new CropConfig();


    private static Uri buildUri() {
        File cacheFolder = new File(Environment.getExternalStorageDirectory() + File.separator + "crop");
        if (!cacheFolder.exists()) {
            try {
                boolean result = cacheFolder.mkdir();
                Log.d("uri", "generateUri " + cacheFolder + " result: " + (result ? "succeeded" : "failed"));
            } catch (Exception e) {
                Log.e("uri", "generateUri failed: " + cacheFolder, e);
            }
        }
        String name = String.format(Locale.getDefault(), "imagecrop-%d.jpg", System.currentTimeMillis());
        uri = Uri.fromFile(new File(cacheFolder, name));
        return uri;
    }

    public static void cropFromCamera(Activity context) {
        cropFromCamera(context, null, TYPE_AVATAR);
    }

    public static void setType(int type) {
        if (type == TYPE_AVATAR) {
            config.isOval = true;
            config.aspectRatioX = 1;
            config.aspectRatioY = 1;
            config.hideBottomControls = true;
            config.showGridLine = false;
            config.showOutLine = false;
            config.maxHeight = 400;
            config.maxWidth = 400;
        } else if (type == TYPE_NORMAL) {//什么都不用做

        } else {

        }
    }

    public static void cropFromCamera(Activity context, PhotoPickUtils.CropConfig config, int type) {
        if (config != null) {
            PhotoPickUtils.config = config;
        } else {
            PhotoPickUtils.config = new PhotoPickUtils.CropConfig();
        }

        setType(type);

        Uri mDestinationUri = buildUri();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                .putExtra(MediaStore.EXTRA_OUTPUT, mDestinationUri);
        context.startActivityForResult(intent, REQUEST_CAMERA);
    }

    public static void startCropActivity(Activity context, Uri sourceUri) {
        Uri mDestinationUri = buildUri();
        UCrop uCrop = UCrop.of(sourceUri, mDestinationUri);

        uCrop.withAspectRatio(config.aspectRatioX, config.aspectRatioY);
        uCrop.withMaxResultSize(config.maxWidth, config.maxHeight);

        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.NONE, UCropActivity.NONE);
        options.setCompressionQuality(config.quality);
        options.setCircleDimmedLayer(config.isOval);
        options.setShowCropGrid(config.showGridLine);
        options.setHideBottomControls(config.hideBottomControls);
        options.setShowCropFrame(config.showOutLine);
        options.setToolbarColor(config.toolbarColor);
        options.setStatusBarColor(config.statusBarColor);

        uCrop.withOptions(options);
        uCrop.start(context, PhotoPicker.REQUEST_CODE);
    }

    public static class CropConfig {
        public int aspectRatioX = 1;
        public int aspectRatioY = 1;
        public int maxWidth = 1080;
        public int maxHeight = 1920;

        //options
        public int tag;
        public boolean isOval = true;//是否为椭圆
        public int quality = 100;

        public boolean hideBottomControls = true;//底部操作条
        public boolean showGridLine = false;//内部网格
        public boolean showOutLine = false;//最外面的矩形线

        public
        @ColorInt
        int toolbarColor = StatusBarCompat.COLOR_DEFAULT;
        public
        @ColorInt
        int statusBarColor = StatusBarCompat.COLOR_DEFAULT;

        public void setHideBottomControls(boolean hideBottomControls) {
            PhotoPickUtils.config.hideBottomControls = hideBottomControls;
        }

        public void setOval(boolean oval) {
            PhotoPickUtils.config.isOval = oval;
        }

        public void setQuality(int quality) {
            PhotoPickUtils.config.quality = quality;
        }

        public void setShowGridLine(boolean showGridLine) {
            PhotoPickUtils.config.showGridLine = showGridLine;
        }

        public void setShowOutLine(boolean showOutLine) {
            PhotoPickUtils.config.showOutLine = showOutLine;
        }

        public void setStatusBarColor(int statusBarColor) {
            PhotoPickUtils.config.statusBarColor = statusBarColor;
        }

        public void setTag(int tag) {
            PhotoPickUtils.config.tag = tag;
        }

        public void setToolbarColor(int toolbarColor) {
            PhotoPickUtils.config.toolbarColor = toolbarColor;
        }

        public void setAspectRation(int x, int y) {
            PhotoPickUtils.config.aspectRatioX = x;
            PhotoPickUtils.config.aspectRatioY = y;
        }

        public void setMaxSize(int width, int height) {
            PhotoPickUtils.config.maxHeight = height;
            PhotoPickUtils.config.maxWidth = width;
        }
    }

    public interface CropHandler {
        void handleCropResult(Uri uri, int tag);

        void handleCropError(Intent data);
    }

    /**
     * 剪裁回調
     */
    public static void onCropResult(Activity context, int requestCode, int resultCode, Intent data, PhotoPickUtils.CropHandler cropHandler) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PhotoPicker.REQUEST_CODE) {//第二次返回，图片已经剪切好
                Uri finalUri = UCrop.getOutput(data);
                cropHandler.handleCropResult(finalUri, config.tag);
            } else if (requestCode == REQUEST_CAMERA) {//第一次，拍照后返回，因为设置了MediaStore.EXTRA_OUTPUT，所以data为null，数据直接就在uri中
                startCropActivity(context, uri);
            }
        }
        if (resultCode == UCrop.RESULT_ERROR) {
            cropHandler.handleCropError(data);
        }
    }

    /**
     * 不剪裁回調
     */
    public static void onActivityResult(Context context, int requestCode, int resultCode, final Intent data, final PickHandler pickHandler) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PhotoPicker.REQUEST_CODE) {//第一次，选择图片后返回
                if (data != null) {
                    ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    pickHandler.onPickSuccess(photos);
                } else {
                    pickHandler.onPickFail("选择图片失败");
                }
            } else if (requestCode == PhotoPicker.TAKE_PHOTO_REQUEST_CODE) {//拍照
                if (PhotoPicker.mTakePhotoUri != null) {
                    final String path = mTakePhotoUri.getPath();
                    if (new File(path).exists()) {
                        String[] paths = {path};
                        String[] mimeTypes = {"image/jpeg"};
                        MediaScannerConnection.scanFile(context, paths, mimeTypes, new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                            }
                        });
                        ArrayList<String> photos = new ArrayList<>();
                        photos.add(path);
                        pickHandler.onPickSuccess(photos);
                    }
                } else {
                    pickHandler.onPickFail("拍照失败");
                }
            } else if (requestCode == PhotoPreview.REQUEST_CODE) {//如果是预览与删除后返回
                if (data != null) {
                    ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    pickHandler.onPreviewBack(photos);
                }
            }
        } else {
            if (requestCode == PhotoPicker.REQUEST_CODE) {
                pickHandler.onPickCancel();
            }
        }
    }

    /**
     * 开始选择，默认最多9张
     *
     * @param context        上下文
     * @param selectedPhotos 已选择的图片
     */
    public static void startPick(Activity context, ArrayList<String> selectedPhotos) {
        PhotoPicker.builder()
                .setPhotoCount(9)
                .setShowCamera(true)
                .setShowGif(true)
                .setSelected(selectedPhotos)
                .setPreviewEnabled(true)
                .start(context, PhotoPicker.REQUEST_CODE);
    }

    /**
     * 打开相机
     *
     * @param context 上下文
     */
    public static void startCamera(Activity context) {
        PhotoPicker.builder().startCamera(context);
    }

    /**
     * 从相册选择单张图片
     *
     * @param context    上下文
     * @param showCamera 是否显示相机
     * @param isCroped   是否剪裁
     */
    public static void startGallerySingle(Activity context, boolean showCamera, boolean isCroped) {
        PhotoPicker.builder()
                .setPhotoCount(1)
                .setShowCamera(showCamera)
                .setCrop(isCroped)
                .setPreviewEnabled(true)
                .start(context);
    }

    /**
     * 从相册选择多张图片
     *
     * @param context        上下文
     * @param showCamera     是否显示相机
     * @param maxCount       最大选择图片数量
     * @param selectedPhotos 已经选择了的图片
     */
    public static void startGalleryMultiple(Activity context, boolean showCamera, int maxCount, ArrayList<String> selectedPhotos) {
        PhotoPicker.builder()
                .setPhotoCount(maxCount)
                .setShowCamera(showCamera)
                .setSelected(selectedPhotos)
                .setPreviewEnabled(true)
                .start(context, PhotoPicker.REQUEST_CODE);
    }

    public interface PickHandler {
        void onPickSuccess(ArrayList<String> photos);

        void onPreviewBack(ArrayList<String> photos);

        void onPickFail(String error);

        void onPickCancel();
    }
}
