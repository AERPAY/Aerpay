package com.client.aerpaymerchant.preference

import android.content.Context
import android.content.SharedPreferences
import com.client.aerpaymerchant.model.User
import com.google.gson.Gson


class PreferenceProvider(
    context: Context
) {
    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = appContext.getSharedPreferences(
            PreferenceConstant.KEY_SHARED_PREFERENCE,
            Context.MODE_PRIVATE
        )

    fun saveUserID(value: String) {
        preference.edit().putString(PreferenceConstant.KEY_USER_ID, value).apply()
    }

    fun getUserID(): String? {
        return preference.getString(PreferenceConstant.KEY_USER_ID, "")
    }

    fun saveStoreID(value: String) {
        preference.edit().putString(PreferenceConstant.KEY_STORE_ID, value).apply()
    }

    fun getStoreID(): String? {
        return preference.getString(PreferenceConstant.KEY_STORE_ID, "")
    }

    fun saveUser(value: User) {
        preference.edit().putString(PreferenceConstant.KEY_USER,Gson().toJson(value)).apply()
    }

    fun getUser(): User? {
        return Gson().fromJson(preference.getString(PreferenceConstant.KEY_USER, ""),User::class.java);
    }

    fun saveProgramID(value: String) {
        preference.edit().putString(PreferenceConstant.KEY_PROGRAM_ID, value).apply()
    }

    fun getProgramID(): String? {
        return preference.getString(PreferenceConstant.KEY_PROGRAM_ID,"")
    }

    fun saveUserFullName(value: String) {
        preference.edit().putString(PreferenceConstant.KEY_FULL_NAME, value).apply()
    }

    fun getUserFullName(): String? {
        return preference.getString(PreferenceConstant.KEY_FULL_NAME,"")
    }

    fun saveUserProfileImage(value: String) {
        preference.edit().putString(PreferenceConstant.KEY_USER_PROFILE_IMAGE, value).apply()
    }

    fun getUserProfileImage(): String? {
        return preference.getString(
            PreferenceConstant.KEY_USER_PROFILE_IMAGE,
           ""
        )
    }

    fun saveEmailID(value: String) {
        preference.edit().putString(PreferenceConstant.KEY_EMAIL_ID, value)
            .apply()
    }

    fun getEmailID(): String? {
        return preference.getString(PreferenceConstant.KEY_EMAIL_ID,"")
    }

    fun saveFCMToken(value: String) {
        preference.edit().putString(PreferenceConstant.KEY_FCM_TOKEN, value)
            .apply()
    }

    fun getFCMToken(): String? {
        return preference.getString(PreferenceConstant.KEY_FCM_TOKEN,"")
    }

    fun saveIsUserLoggedIn(value: Boolean) {
        preference.edit().putBoolean(PreferenceConstant.KEY_USER_LOGGED_IN, value)
            .apply()
    }

    fun getIsUserLoggedIn(): Boolean {
        return preference.getBoolean(
            PreferenceConstant.KEY_USER_LOGGED_IN, false
        )
    }

    fun spClearAll() {
        preference.edit().clear().apply()
    }

    fun spSetInt(key: String, value: Int) = preference.edit().putInt(key, value).apply()

    fun spGetInt(key: String, defaultValue: Int = 0) = preference.getInt(key, defaultValue)

    fun spSetLong(key: String, value: Long) = preference.edit().putLong(key, value).apply()

    fun spGetLong(key: String, defaultValue: Long = 0L) = preference.getLong(key, defaultValue)

    fun spSetFloat(key: String, value: Float) = preference.edit().putFloat(key, value).apply()

    fun spGetFloat(key: String, defaultValue: Float = 0f) = preference.getFloat(key, defaultValue)

    fun spSetBoolean(key: String, value: Boolean) = preference.edit().putBoolean(key, value).apply()

    fun spGetBoolean(key: String, defaultValue: Boolean = false) =
        preference.getBoolean(key, defaultValue)

    fun spSetString(key: String, value: String) = preference.edit().putString(key, value).apply()

    fun spGetString(key: String, defaultValue: String = "") =
        preference.getString(key, defaultValue)!!

    fun spRemove(key: String) = preference.edit().remove(key).apply()
}