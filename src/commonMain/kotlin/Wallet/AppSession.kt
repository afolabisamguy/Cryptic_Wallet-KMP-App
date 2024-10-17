package Wallet

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okio.Path.Companion.toPath


object AppSession {
    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn: StateFlow<Boolean?> get() = _isLoggedIn

    fun updateLoginState(isLoggedIn: Boolean) {
        _isLoggedIn.value = isLoggedIn
    }
}

internal const val dataStoreFileName = "settings.preferences_pb"

object AppSettings{
    private lateinit var dataStore: DataStore<Preferences>
    private val lock = SynchronizedObject()

    fun getDataStore(producePath: () -> String): DataStore<Preferences>{
        return synchronized(lock){
            if(::dataStore.isInitialized){
                dataStore
            } else{
                PreferenceDataStoreFactory.createWithPath(
                    produceFile = { producePath().toPath()}
                ).also { dataStore = it }
            }
        }
    }
}