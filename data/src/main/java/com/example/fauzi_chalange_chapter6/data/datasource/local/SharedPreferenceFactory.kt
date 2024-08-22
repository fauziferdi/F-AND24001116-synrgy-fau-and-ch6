package com.example.fauzi_chalange_chapter6.data.datasource.local

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceFactory {
    companion object{
        const val SHARED_PREFERENCE_NAME = "shared_preference_name"
    }

    fun createSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }
}