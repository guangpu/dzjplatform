package com.jingchengsoft.dzjplatform.sp

import com.jingchengsoft.dzjplatform.common.ext.Preference

/**
 * @author xxh
 * @date 2019/11/6
 * @desc .
 */


object DelegatesExt {

    fun <T> userPreference(name: String, default: T) =
        Preference("user", name, default)
}