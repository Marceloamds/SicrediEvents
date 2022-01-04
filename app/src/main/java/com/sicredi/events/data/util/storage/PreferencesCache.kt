package com.sicredi.events.data.util.storage

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.reflect.Type

class PreferencesCache : Cache {

    companion object {

        private const val PREFS_NAME = "APP_PREFERENCES"

        lateinit var sharedPreferences: SharedPreferences
            private set

        fun init(context: Context) {
            sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        }
    }

    private val gson = Gson()

    override fun <T> get(key: String, type: Type): T? {
        val stringValue = sharedPreferences.getString(key, null)
        return gson.fromJson(stringValue, type)
    }

    override fun set(key: String, value: Any?) {
        if (value == null) {
            sharedPreferences.edit().remove(key).apply()
        } else {
            sharedPreferences.edit().putString(key, gson.toJson(value)).apply()
        }
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}
