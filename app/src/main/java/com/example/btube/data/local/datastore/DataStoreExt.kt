package com.example.btube.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
 * DataStore扩展
 * 用于访问应用的 Preferences DataStore
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "btube_prefs")
