package Wallet

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.db.SqlDriver
import com.russhwolf.settings.Settings
import com.softartdev.theme.pref.ThemeEnum
import viewModels.CryptoSwitchStateManager

expect class WalletCore() {
    fun createWallet() : String
    suspend fun storeWallet(walletName: String, mnemonic: String)
    suspend fun retrieveWallet(walletName: String): Boolean
    suspend fun deleteWallet(walletName: String)
    fun getSymbol(): List<String>
    fun getName(): List<String>
    fun getEarnCoins(): List<String>
    fun getEarnSymbol(): List<String>
    suspend fun getAddress(coin: String): String
    fun getNetworkName(): List<String>
    fun getFullNetworkName(): List<String>
    fun getFullNetworkSymbol(): List<String>
}


expect class UrlOpener(){
    fun openUrl(url: String)

}

expect class MyClipboardManager(){
    fun copyToClipboard(text: String)
    fun showToast(text: String)
}

expect class QRCodeGenerator(){
    fun generateQRCode(text: String): ByteArray
    fun qrImage(text: String): ImageBitmap
    fun scanQRCode(onResult: (String) -> Unit)
}

@Composable
expect fun setSystemBar(newTheme: Boolean)

expect fun createDataStore(): DataStore<Preferences>

expect class DriverFactory() {
    fun createDriver(): SqlDriver
}

expect fun byteArrayToimage(byteArray: ByteArray): ImageBitmap?

lateinit var settingsManager: CryptoSwitchStateManager

fun initializeSetttingsManager(settings: Settings){
    settingsManager = CryptoSwitchStateManager(settings)
}