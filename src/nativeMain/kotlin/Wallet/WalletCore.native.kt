package Wallet

import androidx.compose.runtime.Composable

actual class WalletCore actual constructor() {
    actual fun createWallet(): String {
        TODO("Not yet implemented")
    }

    actual suspend fun storeWallet(walletName: String, mnemonic: String) {
    }

    actual suspend fun retrieveWallet(walletName: String): Boolean {
        return true
    }

    actual suspend fun deleteWallet(walletName: String) {
    }
}

actual class UrlOpener actual constructor() {
    @Composable
    actual fun openUrl(url: String) {
    }

}

actual class MyClipboardManager {
    actual fun copyToClipboard(text: String) {
    }
}