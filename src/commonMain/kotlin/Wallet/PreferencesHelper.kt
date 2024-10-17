package Wallet

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set


class PreferencesHelper(private val settings: Settings) {
    fun isLoggedIn(): Boolean {
        return settings.getBoolean("isLoggedIn", false)
    }

    fun setLoggedIn(loggedIn: Boolean) {
        settings["isLoggedIn"] = loggedIn
    }

}



