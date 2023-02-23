package com.lx.common.ext

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.lx.common.BaseApplication.Companion.context

/**
 * 获取DataStore实例。
 */
val dataStore: DataStore<Preferences> = context.dataStore