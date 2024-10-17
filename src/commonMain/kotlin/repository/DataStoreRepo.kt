package repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class DataStoreRepo(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val TIMESTAMP_KEY = longPreferencesKey(name = "saved_timestamp")
        val LOGIN_STATE_KEY = booleanPreferencesKey(name = "login_state")
    }

    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn: StateFlow<Boolean?> get() = _isLoggedIn

    init {
        readLoginState()
    }


   fun readLoginState() {
        dataStore.data
            .catch { emit(emptyPreferences()) }
            .map { preferences ->
                preferences[LOGIN_STATE_KEY] ?: false
            }
            .onEach { isLoggedIn ->
                _isLoggedIn.value = isLoggedIn
            }
            .launchIn(CoroutineScope(Dispatchers.IO))
    }

   suspend fun updateLoginState(isLoggedIn: Boolean){
        dataStore.edit { preferences ->
            preferences[LOGIN_STATE_KEY] = isLoggedIn
        }
       _isLoggedIn.value = isLoggedIn
   }



    suspend fun saveTimeStamp(timestamp: Long): Boolean =
        try {
            dataStore.edit { preferences ->
                preferences.set(key = TIMESTAMP_KEY, value = timestamp)
            }
            true
        } catch (e: Exception) {
            println("saveTimeStamp() Error $e")
            false
        }

    fun readTimestamp(): Flow<Long> =
        dataStore.data
            .catch { emptyFlow<Long>() }
            .map { preferences ->
                preferences[TIMESTAMP_KEY] ?: 0L
            }
}
