package com.example.splitandpay.user

import android.content.Context

public class UserDataHolder(context: Context) {

    private val preference = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    private companion object {
        private const val PREFS_FILENAME = "com.example.splitandpay.user"

        private const val USER_ID = "user_id"
        private const val USERNAME = "username"
        private const val SHORT_NAME = "short_name"
    }

    var userId: String?
        get() = getString(USER_ID)
        set(value) {
            putString(USER_ID, value)
        }

    var username: String?
        get() = getString(USERNAME)
        set(value) {
            putString(USERNAME, value)
        }

    var shortName: String?
        get() = getString(SHORT_NAME)
        set(value) {
            putString(SHORT_NAME, value)
        }

    private fun getString(key: String): String? = preference.getString(key, null)

    private fun putString(key: String, value: String?) {
        preference.edit().putString(key, value).apply()
    }
}
