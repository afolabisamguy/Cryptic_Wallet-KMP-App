package Wallet

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.db.SqlDriver
import com.softartdev.theme.pref.ThemeEnum

actual class QRCodeGenerator actual constructor() {
    actual fun generateQRCode(text: String): ByteArray {
        TODO("Not yet implemented")
    }

    actual fun qrImage(text: String): ImageBitmap {
        TODO("Not yet implemented")
    }
}

actual fun setSystemBar() {
}

actual fun setSystemBar(newTheme: ThemeEnum): Color {
    TODO("Not yet implemented")
}

actual fun setSystemBar(newTheme: Boolean) {
}

actual fun createDataStore(): DataStore<Preferences> {
    TODO("Not yet implemented")
}

actual class DriverFactory actual constructor() {
    actual fun createDriver(): SqlDriver {
        TODO("Not yet implemented")
    }
}