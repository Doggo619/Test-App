package com.base.unscramblewords.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SharedPrefHelper {

    private static SharedPrefHelper sharedPrefHelper;
    private static Context applicationContext;
    private SharedPreferences sharedpreferences;

    public static final long SHARED_PREFERENCE_DEFAULT_LONG = 0;
    public static final int SHARED_PREFERENCE_DEFAULT_INT = -1;
    private static final String SHARED_PREFERENCE_USER_ROLL_NUMBER = "user_roll_number";
    private static final String SHARED_PREFERENCE_USER_ID = "user_id";
    private static final String SHARED_PREFERENCE_USER_PROFILE_ID = "profile_id";
    private static final String SHARED_PREFERENCE_USER_NAME = "user_name";
    private static final String SHARED_PREFERENCE_USER_EMAIL = "user_email";
    private static final String SHARED_PREFERENCE_STANDARD = "user_class";
    private static final String SHARED_PREFERENCE_USER_PHONE_NUMBER = "user_phone_number";
    public static final String SHARED_PREFERENCE_TIME_LEFT_IN_MILLIS= "time_left_in_millis";
    public static final String SHARED_PREFERENCE_END_TIME= "end_left_in_millis";
    public static final String SHARED_PREFERENCE_DEFAULT_STRING = "";
    public static final String SHARED_PREFERENCES_SELECTED_CLASS = "selected_class";
    public static final String SHARED_PREFERENCES_SELECTED_BOARD = "selected_board";
    public static final String SHARED_PREFERENCES_SELECTED_BOARD_ID = "selected_board_id";
    private static final String SHARED_PREFERENCE_IS_USER_LOGGED = "is_user_logged";
    private static final String SHARED_PREFERENCES_APP_VERSION_TO_BE_UPDATED = "user_app_version_to_be_updated";
    private static final String SHARED_PREFERENCES_APP_VERSION_TYPE = "user_app_version_type";
    private static final String SHARED_PREFERENCES_DATE = "date";
    private static final String SHARED_PREFERENCES_FCM_TOKEN = "fcm_token";
    private static final String SHARED_PREFERENCE_DEVICE_TOKEN = "user_token";
    private static final String SHARED_PREFERENCES_SERVER_DEVICE_ID= "server_device_id";
    private static final String SHARED_PREFERENCES_DEVICE_ID= "device_id";
    private static final String SHARED_PREFERENCES_APP_VERSION_CODE = "user_app_version_code";
    private static final String SHARED_PREFERENCE_NEW_LOGIN = "new_login";
    private static final String SHARED_PREFERENCES_TEST_DESTROY_TIME = "test_destroy_time";
    private static final String SHARED_PREFERENCES_TEST_PAUSE_TIME = "test_pause_time";
    private static final String SHARED_PREFERENCES_TEST_RESUME_TIME = "test_resume_time";
    private static final String SHARED_PREFERENCES_SUPPORTED_BOARDS= "supported_boards";
    private static final String SHARED_PREFERENCES_SUPPORTED_CLASSES = "supported_classes";
    private static final String ANOTHER_SERVER = "another_server";

    public static synchronized SharedPrefHelper getInstance(Context context) {
        //using application context is recommended.
        if (sharedPrefHelper == null) {
            applicationContext = context;
            sharedPrefHelper = new SharedPrefHelper();
        }
        return sharedPrefHelper;
    }

    public SharedPrefHelper() {
        createSharedPref();
    }

    private void createSharedPref() {
        String sharedPrefName = "SharedPreference";
        sharedpreferences = applicationContext.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
    }


    public String getStringPreference(String key, String defaultValue) {
        String value = null;
        if (sharedpreferences != null) {
            value = sharedpreferences.getString(key, defaultValue);
        }
        return value;
    }

    public boolean setStringPreference(String key, String value) {
        if (sharedpreferences != null && !TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(key, value);
            return editor.commit();
        }
        return false;
    }

    public int getUserId() {

        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_INT;

        return sharedpreferences.getInt(SHARED_PREFERENCE_USER_ID, SHARED_PREFERENCE_DEFAULT_INT);

    }

    public void setUserId(int userId) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putInt(SHARED_PREFERENCE_USER_ID, userId);

        editor.apply();

    }

    public int getUserProfileId() {

        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_INT;

        return sharedpreferences.getInt(SHARED_PREFERENCE_USER_PROFILE_ID, SHARED_PREFERENCE_DEFAULT_INT);

    }

    public void setUserProfileId(int profileId) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putInt(SHARED_PREFERENCE_USER_PROFILE_ID, profileId);

        editor.apply();

    }

    public String getUserEmail() {

        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_STRING;

        return sharedpreferences.getString(SHARED_PREFERENCE_USER_EMAIL, SHARED_PREFERENCE_DEFAULT_STRING);

    }

    public void setEmail(String email) {
        if (sharedpreferences == null) {
            createSharedPref();
        }
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(SHARED_PREFERENCE_USER_EMAIL, email);
        editor.apply();
    }

    public String getStandard() {

        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_STRING;

        return sharedpreferences.getString(SHARED_PREFERENCE_STANDARD, SHARED_PREFERENCE_DEFAULT_STRING);

    }

    public void setStandard(String Standard) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(SHARED_PREFERENCE_STANDARD, Standard);

        editor.apply();

    }

    public String getUserName() {

        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_STRING;

        return sharedpreferences.getString(SHARED_PREFERENCE_USER_NAME, SHARED_PREFERENCE_DEFAULT_STRING);

    }

    public void setUserName(String name) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(SHARED_PREFERENCE_USER_NAME, name);

        editor.apply();

    }

    public long getRemainingTimeInMillis() {

        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_LONG;

        return sharedpreferences.getLong(SHARED_PREFERENCE_TIME_LEFT_IN_MILLIS, SHARED_PREFERENCE_DEFAULT_LONG);

    }

    public void setRemainingTimeInMillis(long timeLeftInMillis) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putLong(SHARED_PREFERENCE_TIME_LEFT_IN_MILLIS, timeLeftInMillis);

        editor.apply();
    }

    public long getEndTime() {

        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_LONG;

        return sharedpreferences.getLong(SHARED_PREFERENCE_END_TIME, SHARED_PREFERENCE_DEFAULT_LONG);

    }

    public void setEndTime(long endTime) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putLong(SHARED_PREFERENCE_END_TIME, endTime);

        editor.apply();
    }

    public int getSelectedClass() {

        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_INT;

        return sharedpreferences.getInt(SHARED_PREFERENCES_SELECTED_CLASS, SHARED_PREFERENCE_DEFAULT_INT);

    }

    public void setSelectedClass(int selectedClass) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(SHARED_PREFERENCES_SELECTED_CLASS, selectedClass);

        editor.apply();
    }

    public String getSelectedBoard() {

        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_STRING;

        return sharedpreferences.getString(SHARED_PREFERENCES_SELECTED_BOARD, "");

    }

    public void setSelectedBoard(String selectedBoard) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(SHARED_PREFERENCES_SELECTED_BOARD, selectedBoard);

        editor.apply();
    }

    public int getSelectedBoardId() {

        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_INT;

        return sharedpreferences.getInt(SHARED_PREFERENCES_SELECTED_BOARD_ID, SHARED_PREFERENCE_DEFAULT_INT);

    }

    public void setSelectedBoardId(int selectedBoardId) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(SHARED_PREFERENCES_SELECTED_BOARD_ID, selectedBoardId);

        editor.apply();
    }

    public boolean getIsUserLogged() {

        if (sharedpreferences == null)
            return false;

        return sharedpreferences.getBoolean(SHARED_PREFERENCE_IS_USER_LOGGED, false);

    }

    public void setIsUserLogged(boolean isUserLogged) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putBoolean(SHARED_PREFERENCE_IS_USER_LOGGED, isUserLogged);

        editor.apply();
    }

    public int getAppVersionToBeUpdated() {

        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_INT;

        return sharedpreferences.getInt(SHARED_PREFERENCES_APP_VERSION_TO_BE_UPDATED, SHARED_PREFERENCE_DEFAULT_INT);

    }

    public void setAppVersionCodeAppVersionToBeUpdated(int versionCode) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putInt(SHARED_PREFERENCES_APP_VERSION_TO_BE_UPDATED, versionCode);

        editor.apply();
    }

    public int getVersionType(){
        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_INT;

        return sharedpreferences.getInt(SHARED_PREFERENCES_APP_VERSION_TYPE, SHARED_PREFERENCE_DEFAULT_INT);

    }

    public void setVersionType(int VersionType){
        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putInt(SHARED_PREFERENCES_APP_VERSION_TYPE, VersionType);

        editor.apply();

    }

    public long getUpdateCalledDate(){
        if(sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_LONG;
        return sharedpreferences.getLong(SHARED_PREFERENCES_DATE,SHARED_PREFERENCE_DEFAULT_LONG);
    }

    public void setUpdateCalledDate(long date) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putLong(SHARED_PREFERENCES_DATE, date);

        editor.apply();

    }

    public String getFcmToken() {

        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_STRING;

        return sharedpreferences.getString(SHARED_PREFERENCES_FCM_TOKEN, "");

    }

    public void setFcmToken(String fcmToken) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(SHARED_PREFERENCES_FCM_TOKEN, fcmToken);

        editor.apply();
    }

    public String getUserRollNumber() {

        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_STRING;

        return sharedpreferences.getString(SHARED_PREFERENCE_USER_ROLL_NUMBER, SHARED_PREFERENCE_DEFAULT_STRING);

    }

    public void setUserRollNumber(String userRollNumber) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(SHARED_PREFERENCE_USER_ROLL_NUMBER, userRollNumber);

        editor.apply();

    }

    public String getDeviceToken() {

        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_STRING;

        return sharedpreferences.getString(SHARED_PREFERENCE_DEVICE_TOKEN, SHARED_PREFERENCE_DEFAULT_STRING);

    }

    public void setDeviceToken(String token) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(SHARED_PREFERENCE_DEVICE_TOKEN, token);

        editor.apply();

    }

    public int getServerDeviceId(){
        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_INT;

        return sharedpreferences.getInt(SHARED_PREFERENCES_SERVER_DEVICE_ID, SHARED_PREFERENCE_DEFAULT_INT);

    }

    public void setServerDeviceId(int serverDeviceId){
        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putInt(SHARED_PREFERENCES_SERVER_DEVICE_ID, serverDeviceId);

        editor.apply();

    }

    public String getDeviceId(){
        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_STRING;

        return sharedpreferences.getString(SHARED_PREFERENCES_DEVICE_ID, SHARED_PREFERENCE_DEFAULT_STRING);

    }

    public void setDeviceId(String deviceId){
        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(SHARED_PREFERENCES_DEVICE_ID, deviceId);

        editor.apply();

    }

    public int getAppVersionCode() {

        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_INT;

        return sharedpreferences.getInt(SHARED_PREFERENCES_APP_VERSION_CODE, SHARED_PREFERENCE_DEFAULT_INT);

    }

    public void setAppVersionCode(int versionCode) {

        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putInt(SHARED_PREFERENCES_APP_VERSION_CODE, versionCode);

        editor.apply();
    }

    public void clearPref() {
        sharedpreferences.edit().clear().apply();
    }



    public void setUserPhoneNumber(String phoneNumber) {
        if (sharedpreferences == null) {
            createSharedPref();
        }
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(SHARED_PREFERENCE_USER_PHONE_NUMBER, phoneNumber);
        editor.apply();
    }

    public String getUserPhoneNumber() {
        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_STRING;
        return sharedpreferences.getString(SHARED_PREFERENCE_USER_PHONE_NUMBER, SHARED_PREFERENCE_DEFAULT_STRING);
    }

    public long getDestroyedTime(){
        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_LONG;

        return sharedpreferences.getLong(SHARED_PREFERENCES_TEST_DESTROY_TIME, SHARED_PREFERENCE_DEFAULT_LONG);

    }

    public void setDestroyedTime(long elapsedTime){
        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putLong(SHARED_PREFERENCES_TEST_DESTROY_TIME, elapsedTime);

        editor.apply();

    }

    public long getPausedTime(){
        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_LONG;

        return sharedpreferences.getLong(SHARED_PREFERENCES_TEST_PAUSE_TIME, SHARED_PREFERENCE_DEFAULT_LONG);

    }

    public void setPausedTime(long pausedTime){
        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putLong(SHARED_PREFERENCES_TEST_PAUSE_TIME, pausedTime);

        editor.apply();

    }

    public long getResumedTime(){
        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_LONG;

        return sharedpreferences.getLong(SHARED_PREFERENCES_TEST_RESUME_TIME, SHARED_PREFERENCE_DEFAULT_LONG);

    }

    public void setResumedTime(long resumedTime){
        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putLong(SHARED_PREFERENCES_TEST_RESUME_TIME, resumedTime);

        editor.apply();

    }

    public void setSupportedBoards(String boards){
        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(SHARED_PREFERENCES_SUPPORTED_BOARDS, boards);

        editor.apply();

    }

    public String getSupportedBoards(){
        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_STRING;

        return sharedpreferences.getString(SHARED_PREFERENCES_SUPPORTED_BOARDS, SHARED_PREFERENCE_DEFAULT_STRING);
    }

    public void setSupportedClass(String boards){
        if (sharedpreferences == null)
            createSharedPref();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(SHARED_PREFERENCES_SUPPORTED_CLASSES, boards);

        editor.apply();

    }

    public String getSupportedClass(){
        if (sharedpreferences == null)
            return SHARED_PREFERENCE_DEFAULT_STRING;

        return sharedpreferences.getString(SHARED_PREFERENCES_SUPPORTED_CLASSES, SHARED_PREFERENCE_DEFAULT_STRING);
    }
}
