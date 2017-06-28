package com.livelearn.ignorance.utils;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.apkfuns.logutils.LogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.UUID;

/**
 * 设备信息
 */
public class DeviceUtils {

    /**
     * 判断SDCard是否可用
     */
    public static boolean existSDCard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * Get uuid
     */
    public static String getUUID(Context context) {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, tmPhone, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider
                .Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return deviceUuid.toString();
    }

    /**
     * Get screen brightness mode，must declare the
     * {@link android.Manifest.permission#WRITE_SETTINGS} permission in its manifest.
     */
    public static int getScreenBrightnessMode(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
    }

    /**
     * Judge screen brightness mode is auto mode，must declare the
     * {@link android.Manifest.permission#WRITE_SETTINGS} permission in its manifest.
     *
     * @return true:auto;false: manual;default:true
     */
    public static boolean isScreenBrightnessModeAuto(Context context) {
        return getScreenBrightnessMode(context) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
    }

    /**
     * Set screen brightness mode，must declare the
     * {@link android.Manifest.permission#WRITE_SETTINGS} permission in its manifest.
     */
    public static boolean setScreenBrightnessMode(Context context, boolean auto) {
        boolean result = true;
        if (isScreenBrightnessModeAuto(context) != auto) {
            result = Settings.System.putInt(context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    auto ? Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
                            : Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        }
        return result;
    }

    /**
     * Get screen brightness, must declare the
     * {@link android.Manifest.permission#WRITE_SETTINGS} permission in its manifest.
     *
     * @return brightness:0-255; default:255
     */
    public static int getScreenBrightness(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, 255);
    }

    /**
     * Set screen brightness, cannot change window brightness.must declare the
     * {@link android.Manifest.permission#WRITE_SETTINGS} permission in its manifest.
     *
     * @param screenBrightness 0-255
     */
    public static boolean setScreenBrightness(Context context,
                                              int screenBrightness) {
        int brightness = screenBrightness;
        if (screenBrightness < 1) {
            brightness = 1;
        } else if (screenBrightness > 255) {
            brightness = screenBrightness % 255;
            if (brightness == 0) {
                brightness = 255;
            }
        }
        return Settings.System.putInt(context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, brightness);
    }

    /**
     * Set window brightness, cannot change system brightness.
     *
     * @param screenBrightness 0-255
     */
    public static void setWindowBrightness(Activity activity,
                                           float screenBrightness) {
        float brightness = screenBrightness;
        if (screenBrightness < 1) {
            brightness = 1;
        } else if (screenBrightness > 255) {
            brightness = screenBrightness % 255;
            if (brightness == 0) {
                brightness = 255;
            }
        }
        Window window = activity.getWindow();
        WindowManager.LayoutParams localLayoutParams = window.getAttributes();
        localLayoutParams.screenBrightness = brightness / 255.0f;
        window.setAttributes(localLayoutParams);
    }

    /**
     * Set screen brightness and change system brightness, must declare the
     * {@link android.Manifest.permission#WRITE_SETTINGS} permission in its manifest.
     *
     * @param screenBrightness 0-255
     */
    public static boolean setScreenBrightnessAndApply(Activity activity,
                                                      int screenBrightness) {
        boolean result = setScreenBrightness(activity, screenBrightness);
        if (result) {
            setWindowBrightness(activity, screenBrightness);
        }
        return result;
    }

    /**
     * Get screen dormant time, must declare the
     * {@link android.Manifest.permission#WRITE_SETTINGS} permission in its manifest.
     *
     * @return dormantTime:ms, default:30s
     */
    public static int getScreenDormantTime(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.System.SCREEN_OFF_TIMEOUT, 30000);
    }

    /**
     * Set screen dormant time by millis, must declare the
     * {@link android.Manifest.permission#WRITE_SETTINGS} permission in its manifest.
     */
    public static boolean setScreenDormantTime(Context context, int millis) {
        return Settings.System.putInt(context.getContentResolver(),
                Settings.System.SCREEN_OFF_TIMEOUT, millis);
    }

    /**
     * Get airplane mode, must declare the
     * {@link android.Manifest.permission#WRITE_APN_SETTINGS} permission in its manifest.
     *
     * @return 1:open, 0:close, default:close
     */
    public static int getAirplaneMode(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0);
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0);
        }
    }

    /**
     * Judge whether airplane is open, must declare the
     * {@link android.Manifest.permission#WRITE_APN_SETTINGS} permission in its manifest.
     *
     * @return true:open, false:close, default:close
     */
    public static boolean isAirplaneModeOpen(Context context) {
        return getAirplaneMode(context) == 1;
    }

    /**
     * Set airplane mode, must declare the
     * {@link android.Manifest.permission#WRITE_APN_SETTINGS} permission in its manifest.
     */
    public static boolean setAirplaneMode(Context context, boolean enable) {
        boolean result = true;
        if (isAirplaneModeOpen(context) != enable) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                result = Settings.System.putInt(context.getContentResolver(),
                        Settings.System.AIRPLANE_MODE_ON, enable ? 1 : 0);
            } else {
                result = Settings.Global.putInt(context.getContentResolver(),
                        Settings.Global.AIRPLANE_MODE_ON, enable ? 1 : 0);
            }
            context.sendBroadcast(new Intent(
                    Intent.ACTION_AIRPLANE_MODE_CHANGED));
        }
        return result;
    }

    /**
     * Get bluetooth state
     *
     * @return STATE_OFF, STATE_TURNING_OFF, STATE_ON, STATE_TURNING_ON, NONE
     */
    @RequiresPermission(Manifest.permission.BLUETOOTH)
    public static Integer getBluetoothState() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();
        if (bluetoothAdapter == null) {
            return null;
        } else {
            return bluetoothAdapter.getState();
        }
    }

    /**
     * Judge bluetooth is open
     *
     * @return true:open, false:close.
     */
    public static boolean isBluetoothOpen() {
        Integer bluetoothStateCode = getBluetoothState();
        return bluetoothStateCode != null && (bluetoothStateCode == BluetoothAdapter.STATE_ON || bluetoothStateCode == BluetoothAdapter.STATE_TURNING_ON);
    }

    /**
     * Set bluetooth
     */
    @RequiresPermission(Manifest.permission.BLUETOOTH_ADMIN)
    public static void setBluetooth(boolean enable) throws Exception {
        if (isBluetoothOpen() != enable) {
            if (enable) {
                BluetoothAdapter.getDefaultAdapter().enable();
            } else {
                BluetoothAdapter.getDefaultAdapter().disable();
            }
        }
    }

    /**
     * Get media volume
     *
     * @return volume:0-15
     */
    public static int getMediaVolume(Context context) {
        return ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE)).getStreamVolume(AudioManager
                .STREAM_MUSIC);
    }

    /**
     * Set media volume
     */
    public static void setMediaVolume(Context context, int mediaVloume) {
        if (mediaVloume < 0) {
            mediaVloume = 0;
        } else if (mediaVloume > 15) {
            mediaVloume = mediaVloume % 15;
            if (mediaVloume == 0) {
                mediaVloume = 15;
            }
        }
        ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE)).setStreamVolume(AudioManager.STREAM_MUSIC,
                mediaVloume, AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
    }

    /**
     * Get ring volume
     *
     * @return volume:0-7
     */
    public static int getRingVolume(Context context) {
        return ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE)).getStreamVolume(AudioManager
                .STREAM_RING);
    }

    /**
     * Set ring volume
     */
    public static void setRingVolume(Context context, int ringVloume) {
        if (ringVloume < 0) {
            ringVloume = 0;
        } else if (ringVloume > 7) {
            ringVloume = ringVloume % 7;
            if (ringVloume == 0) {
                ringVloume = 7;
            }
        }
        ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE)).setStreamVolume(AudioManager.STREAM_RING,
                ringVloume, AudioManager.FLAG_PLAY_SOUND);
    }

    /**
     * 获取mac地址,如果获取不了，返回 ""
     */
    public static String getLocalMacAddress() {
        String mac = null;
        try {
            String path = "sys/class/net/wlan0/address";
            if ((new File(path)).exists()) {
                FileInputStream fis = new FileInputStream(path);
                byte[] buffer = new byte[8192];
                int byteCount = fis.read(buffer);
                if (byteCount > 0) {
                    mac = new String(buffer, 0, byteCount, "utf-8");
                }
            }
            if (mac == null || mac.length() == 0) {
                path = "sys/class/net/eth0/address";
                FileInputStream fis_name = new FileInputStream(path);
                byte[] buffer_name = new byte[8192];
                int byteCount_name = fis_name.read(buffer_name);
                if (byteCount_name > 0) {
                    mac = new String(buffer_name, 0, byteCount_name, "utf-8");
                }
            }
            if (mac == null || mac.length() == 0) {
                return "";
            }
            return mac.trim();
        } catch (Exception e) {
            LogUtils.e(e.toString());
        }
        return "";
    }

    /**
     * 获取ipv4地址
     */
    public static String getHostAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        if (inetAddress.getHostAddress() != null) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.e(e.toString());
        }
        return "";
    }

    /**
     * 获取AndroidId
     */
    public static String getAndroidId(Context context) {
        String androidId = "";
        try {
            androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            LogUtils.e(e.toString());
        }
        return androidId;
    }

    /**
     * 获取 gps的描述，如果获取不了，返回""
     */
    public static String getGPS(Context context) {
        String gpsdesc = "";
        try {
            if (PackageUtils.checkPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) ||
                    PackageUtils.checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)) {
                LocationManager gpsManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                Location location = gpsManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    gpsdesc = "精度:" + location.getAccuracy() + ",高度 :"
                            + location.getAltitude() + ",导向 :" + location.getBearing()
                            + ",速度 :" + location.getSpeed() + ", 纬度 :"
                            + location.getLatitude() + ",经度 :"
                            + location.getLongitude();
                }
            }
        } catch (Exception e) {
            LogUtils.e(e.toString());
        }
        return gpsdesc;
    }
}
