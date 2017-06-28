package me.iwf.photopicker.utils;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import android.support.v4.content.CursorLoader;

import static android.provider.MediaStore.MediaColumns.MIME_TYPE;

/**
 * Created by 黄东鲁 on 15/6/28.
 */
class PhotoDirectoryLoader extends CursorLoader {

  PhotoDirectoryLoader(Context context, boolean showGif) {
    super(context);
    String[] IMAGE_PROJECTION = {
            Media._ID,
            Media.DATA,
            Media.BUCKET_ID,
            Media.BUCKET_DISPLAY_NAME,
            Media.DATE_ADDED
    };
    setProjection(IMAGE_PROJECTION);
    setUri(Media.EXTERNAL_CONTENT_URI);
    setSortOrder(Media.DATE_ADDED + " DESC");

    setSelection(
        MIME_TYPE + "=? or " + MIME_TYPE + "=? or "+ MIME_TYPE + "=? " + (showGif ? ("or " + MIME_TYPE + "=?") : ""));
    String[] selectionArgs;
    if (showGif) {
      selectionArgs = new String[] { "image/jpeg", "image/png", "image/jpg","image/gif" };
    } else {
      selectionArgs = new String[] { "image/jpeg", "image/png", "image/jpg" };
    }
    setSelectionArgs(selectionArgs);
  }


  private PhotoDirectoryLoader(Context context, Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    super(context, uri, projection, selection, selectionArgs, sortOrder);
  }
}
