package presentation.screen.settings

import Wallet.setSystemBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShieldMoon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.softartdev.theme.material.PreferenceItem
import com.softartdev.theme.material.ThemeDialog
import com.softartdev.theme.material.ThemePreferenceItem
import com.softartdev.theme.pref.PreferableMaterialTheme.themePrefs
import com.softartdev.theme.pref.PreferenceHelper
import com.softartdev.theme.pref.ThemeEnum
import compose.icons.AllIcons
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.Moon
import compose.icons.fontawesomeicons.regular.Sun
import compose.icons.fontawesomeicons.solid.Wallet
import presentation.screen.WalletActionScreen
import presentation.screen.WalletListScreen
import kotlin.properties.Delegates

@Composable
fun SettingsFirstComp(show: Boolean) {
    val navigator = LocalNavigator.currentOrThrow
    Column {
        if (show) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .clickable {
                        navigator.push(WalletListScreen())
                    }
                    .height(80.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Wallet,
                    contentDescription = "Add",
                    modifier = Modifier.align(Alignment.CenterVertically).size(30.dp)
                )
                Column {
                    Text(
                        text = "Wallet",
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                    Text(
                        text = " MainWallet",
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                }
            }
        }
        val preferenceHelper: PreferenceHelper = themePrefs.preferenceHelper
        ThemeSwitch(
            darkThemeState = themePrefs.darkThemeState,
            writePref = preferenceHelper::themeEnum::set
        )



    }
}

@Composable
fun ThemeSwitch(
    darkThemeState: MutableState<ThemeEnum> = mutableStateOf(ThemeEnum.Light),
    writePref: (ThemeEnum) -> Unit = {}
) {

    val isDarkTheme = darkThemeState.value == ThemeEnum.Dark

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (isDarkTheme) FontAwesomeIcons.Regular.Moon else FontAwesomeIcons.Regular.Sun,
            contentDescription = null,
            modifier = Modifier.size(25.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = (if (isDarkTheme) "dark_theme" else "light_theme"),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.weight(1f)
        )
        setSystemBar(isDarkTheme)
        Switch(
            checked = isDarkTheme,
            onCheckedChange = { isChecked ->
                val newTheme = if (isChecked) ThemeEnum.Dark else ThemeEnum.Light
                darkThemeState.value = newTheme
                writePref(newTheme)
            }
        )
    }
}
