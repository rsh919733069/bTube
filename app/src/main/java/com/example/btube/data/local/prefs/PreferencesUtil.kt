package com.example.btube.data.local.prefs

import android.app.Activity
import android.content.Context
import com.example.btube.core.SHARED_FILE

/**
 * SharedPreferences工具类
 * 封装SharedPreferences的读写操作
 */
class PreferencesUtil(
    private val context: Context
) {
    /**
     * 保存值到SharedPreferences
     */
    fun setValue(key: String, value: Any) {
        context.getSharedPreferences(SHARED_FILE, Activity.MODE_PRIVATE).edit().apply {
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                is Boolean -> putBoolean(key, value)
            }
            commit()
        }
    }
    
    private val prefs = context.getSharedPreferences(SHARED_FILE, Activity.MODE_PRIVATE)

    fun getValue(key: String, defaultValue: String): String =
        prefs.getString(key, defaultValue) ?: defaultValue

    fun getValue(key: String, defaultValue: Int): Int =
        prefs.getInt(key, defaultValue)

    fun getValue(key: String, defaultValue: Long): Long =
        prefs.getLong(key, defaultValue)

    fun getValue(key: String, defaultValue: Float): Float =
        prefs.getFloat(key, defaultValue)

    fun getValue(key: String, defaultValue: Boolean): Boolean =
        prefs.getBoolean(key, defaultValue)
}
