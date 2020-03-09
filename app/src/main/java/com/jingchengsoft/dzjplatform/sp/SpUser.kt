package com.jingchengsoft.dzjplatform.sp

/**
 * @author xxh
 * @date 2019/11/19
 * @desc TODO.
 */

object SpUser {
    var username: String by DelegatesExt.userPreference("username", "")
    var pwd: String by DelegatesExt.userPreference("pwd", "")
    var token: String by DelegatesExt.userPreference("token", "")
}