package ai.chatprism.modlife.utils

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import ai.chatprism.modlife.model.AuthenticationData

const val KEY_NOT_INITIAL_SETUP = "key_initial_setup"
const val KEY_DEVICE_ID = "key_device_id"

//@Singleton
class SharedPrefsHelper (private val mSharedPreferences: SharedPreferences) {
    private val TAG = "SharedPrefsHelper"
    private val KEY_IS_LOGIN = "is_logged_in_toto"
    private val KEY_USER_TOKEN = "key_user_token"
    private val KEY_USER_ID = "UserId"
    private val KEY_USER_OBJECT = "UserData"

    fun put(key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun put(key: String, value: Int) {
        mSharedPreferences.edit().putInt(key, value).apply()
    }

    fun put(key: String, value: Float) {
        mSharedPreferences.edit().putFloat(key, value).apply()
    }

    fun put(key: String, value: Double) {
        mSharedPreferences.edit().putFloat(key, value.toFloat()).apply()
    }

    fun put(key: String, value: Boolean) {
        mSharedPreferences.edit().putBoolean(key, value).apply()
    }

    operator fun get(key: String, defaultValue: String): String? {
        return mSharedPreferences.getString(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Int): Int {
        return mSharedPreferences.getInt(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Float): Float {
        return mSharedPreferences.getFloat(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Boolean): Boolean {
        return mSharedPreferences.getBoolean(key, defaultValue)
    }

    fun setIsLoggedIn(isLogin: Boolean) {
        return put(KEY_IS_LOGIN, isLogin)
    }

    fun isLoggedIn(): Boolean {
        return get(KEY_IS_LOGIN, false) ?: false
    }

    fun setUserId(userId: Int) {
        put(KEY_USER_ID, userId)
    }
    fun getUserId(): Int {
        return get(KEY_USER_ID, -1) ?: -1
    }
    fun setToken(fcm: String){
        put(KEY_USER_TOKEN, fcm)
    }
    fun getToken(): String{
        return get(KEY_USER_TOKEN, "") ?: ""
    }

    fun setUserData(data: AuthenticationData?) {
        val userJson = Gson().toJson(data)
        put(KEY_USER_OBJECT, userJson)
    }

    fun getUserData(): AuthenticationData? {
        return try {
            val json = get(KEY_USER_OBJECT, "")
             Gson().fromJson(json, AuthenticationData::class.java
            )
        }
        catch (e: JsonSyntaxException){
            null
        }
        catch (e: java.lang.Exception){
            null
        }
    }

    fun getMSharedPreferences(): SharedPreferences {
        return mSharedPreferences
    }

    fun clearPrefs() {
        mSharedPreferences.edit().clear().apply()
    }

}