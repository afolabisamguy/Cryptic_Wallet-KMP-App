package presentation.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.brands.ItunesNote
import compose.icons.fontawesomeicons.regular.File
import compose.icons.fontawesomeicons.solid.AddressBook
import compose.icons.fontawesomeicons.solid.DollarSign
import compose.icons.fontawesomeicons.solid.FileContract
import compose.icons.fontawesomeicons.solid.Hammer
import compose.icons.fontawesomeicons.solid.Lock
import compose.icons.fontawesomeicons.solid.Qrcode

val loggedList = listOf("Price Alerts", "Address Book", "Trust handles", "Scan Qr Code", "WalletConnect")

val loggedIcons = mapOf(
    "Price Alerts" to FontAwesomeIcons.Solid.DollarSign,
    "Address Book" to FontAwesomeIcons.Solid.AddressBook,
    "Trust handles" to FontAwesomeIcons.Solid.Hammer,
    "Scan Qr Code" to FontAwesomeIcons.Solid.Qrcode,
    "WalletConnect" to FontAwesomeIcons.Solid.FileContract,
)

val preferenceslist = listOf("Preferences", "Security", "Notifications")

val preferencesicons = mapOf(
    "Preferences" to FontAwesomeIcons.Regular.File,
    "Security" to FontAwesomeIcons.Solid.Lock,
    "Notifications" to FontAwesomeIcons.Brands.ItunesNote,
)


@Composable
fun SettingsSecondComp() {
    Column {
        for (i in loggedList) {
            SettingsComponent(name = i, imageVector = loggedIcons[i]!!, onClick = {})
        }
    }
}


@Composable
fun SettingsThirdComp(show: Boolean) {


    Column {
        if (!show){
            preferenceslist[0].let { preferencesicons[it]?.let { it1 ->
                SettingsComponent(name = it, imageVector = it1,
                    onClick = {})
            } }
        }


        if (show) {
            for (i in preferenceslist) {
                SettingsComponent(name = i, imageVector = preferencesicons[i]!!, onClick = {})
            }
        }
    }
}
