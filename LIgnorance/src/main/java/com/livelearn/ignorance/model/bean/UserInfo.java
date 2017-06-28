package com.livelearn.ignorance.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.mcxtzhang.indexbar.bean.BaseIndexPinyinBean;

import java.io.Serializable;

/**
 * 用户信息
 */
public class UserInfo extends BaseIndexPinyinBean implements Serializable, Parcelable {

    //id
    private String userId;
    //账号
    private String userAccount;
    //昵称
    private String userNickname;
    //头像
    private String userAvatarPath;
    //个性签名
    private String personalizedSignature;
    //性别 0:保密；1：男； 2：女；
    private String gender;
    //生日（yyyy年MM月dd日）
    private String birthday;
    //登录状态 1：在线；2：离线
    private String loginStatus;
    //等级
    private String userLevel;
    //AndroidId
    private String androidId;
    //手机型号
    private String phoneModel;
    //MacAddress
    private String macAddress;
    //HostAddress
    private String hostAddress;
    //GPS
    private String gps;
    //电话号码
    private String phone;
    //email
    private String email;
    //所在省份
    private String province;
    private String provinceCode;
    //所在城市
    private String city;
    private String cityCode;
    private String cityLandscapeUrl;
    //通讯录中是否是最上面的 不需要被转化成拼音的
    private boolean isTop;
    //通讯录显示
    private String target;

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        userId = in.readString();
        userAccount = in.readString();
        userNickname = in.readString();
        userAvatarPath = in.readString();
        personalizedSignature = in.readString();
        gender = in.readString();
        birthday = in.readString();
        loginStatus = in.readString();
        userLevel = in.readString();
        androidId = in.readString();
        phoneModel = in.readString();
        macAddress = in.readString();
        hostAddress = in.readString();
        gps = in.readString();
        phone = in.readString();
        email = in.readString();
        province = in.readString();
        provinceCode = in.readString();
        city = in.readString();
        cityCode = in.readString();
        cityLandscapeUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(userAccount);
        dest.writeString(userNickname);
        dest.writeString(userAvatarPath);
        dest.writeString(personalizedSignature);
        dest.writeString(gender);
        dest.writeString(birthday);
        dest.writeString(loginStatus);
        dest.writeString(userLevel);
        dest.writeString(androidId);
        dest.writeString(phoneModel);
        dest.writeString(macAddress);
        dest.writeString(hostAddress);
        dest.writeString(gps);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(province);
        dest.writeString(provinceCode);
        dest.writeString(city);
        dest.writeString(cityCode);
        dest.writeString(cityLandscapeUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public UserInfo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public UserInfo setUserAccount(String userAccount) {
        this.userAccount = userAccount;
        return this;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public UserInfo setUserNickname(String userNickname) {
        this.userNickname = userNickname;
        return this;
    }

    public String getUserAvatarPath() {
        return userAvatarPath;
    }

    public UserInfo setUserAvatarPath(String userAvatarPath) {
        this.userAvatarPath = userAvatarPath;
        return this;
    }

    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public UserInfo setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public UserInfo setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public UserInfo setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public UserInfo setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
        return this;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public UserInfo setUserLevel(String userLevel) {
        this.userLevel = userLevel;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserInfo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public UserInfo setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public UserInfo setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserInfo setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCityCode() {
        return cityCode;
    }

    public UserInfo setCityCode(String cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public String getCityLandscapeUrl() {
        return cityLandscapeUrl;
    }

    public UserInfo setCityLandscapeUrl(String cityLandscapeUrl) {
        this.cityLandscapeUrl = cityLandscapeUrl;
        return this;
    }

    public String getAndroidId() {
        return androidId;
    }

    public UserInfo setAndroidId(String androidId) {
        this.androidId = androidId;
        return this;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public UserInfo setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
        return this;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public UserInfo setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public UserInfo setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
        return this;
    }

    public String getGps() {
        return gps;
    }

    public UserInfo setGps(String gps) {
        this.gps = gps;
        return this;
    }

    public boolean isTop() {
        return isTop;
    }

    public UserInfo setTop(boolean top) {
        isTop = top;
        return this;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userAvatarPath='" + userAvatarPath + '\'' +
                ", personalizedSignature='" + personalizedSignature + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", loginStatus='" + loginStatus + '\'' +
                ", userLevel='" + userLevel + '\'' +
                ", androidId='" + androidId + '\'' +
                ", phoneModel='" + phoneModel + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", hostAddress='" + hostAddress + '\'' +
                ", gps='" + gps + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", province='" + province + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", city='" + city + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", cityLandscapeUrl='" + cityLandscapeUrl + '\'' +
                ", isTop=" + isTop +
                '}';
    }

    public UserInfo setTarget(String target) {
        this.target = target;
        return this;
    }

    @Override
    public String getTarget() {
        return null == target ? userNickname : target;
    }

    @Override
    public boolean isNeedToPinyin() {
        return !isTop;
    }

    @Override
    public boolean isShowSuspension() {
        return !isTop;
    }
}
