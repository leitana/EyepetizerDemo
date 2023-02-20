package com.lx.common.ext

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.lx.common.BaseApplication
import com.lx.common.BaseApplication.Companion.context

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = context.packageName + "_preferences",
    produceMigrations = { context ->
        listOf(SharedPreferencesMigration(context, context.packageName + "_preferences"))
    })