package com.jingchengsoft.dzjplatform.common.ext

import com.blankj.utilcode.util.SPUtils
import kotlin.reflect.KProperty

/**
 * @author MaybeSix
 * @date 2019/9/25
 * @desc TODO.
 */

@Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
class Preference<T>(private val fileName: String, val name: String, val default: T) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = getSharedPreferences(name, default)

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = putSharedPreferences(name, value)

    private fun putSharedPreferences(name: String, value: T) {
        when (value) {
            is Int -> SPUtils.getInstance(fileName).put(name, value)
            is Float -> SPUtils.getInstance(fileName).put(name, value)
            is Long -> SPUtils.getInstance(fileName).put(name, value)
            is Boolean -> SPUtils.getInstance(fileName).put(name, value)
            is String -> SPUtils.getInstance(fileName).put(name, value)
            else -> throw IllegalArgumentException("SharedPreference can't be save this type")
        }
    }

    private fun getSharedPreferences(name: String, default: T): T {
        return when (default) {
            is Int -> SPUtils.getInstance(fileName).getInt(name, default)
            is Float -> SPUtils.getInstance(fileName).getFloat(name, default)
            is Long -> SPUtils.getInstance(fileName).getLong(name, default)
            is Boolean -> SPUtils.getInstance(fileName).getBoolean(name, default)
            is String -> SPUtils.getInstance(fileName).getString(name, default)
            else -> throw IllegalArgumentException("SharedPreference can't be get this type")
        } as T
    }
}