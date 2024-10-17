package presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.softartdev.theme.material.PreferableMaterialTheme
import com.softartdev.theme.material.SettingsScaffold
import com.softartdev.theme.material.ThemePreferenceItem
import com.softartdev.theme.material.ThemePreferencesCategory
import com.softartdev.theme.pref.PreferableMaterialTheme
import com.softartdev.theme.pref.PreferableMaterialTheme.themePrefs
import viewModels.ReceiveViewModel

class ScanQrScreen: Screen {
    @Composable
    override fun Content() {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "To be implemented")
            Apper()

        }
    }
}

@Composable
fun Apper() = PreferableMaterialTheme { // provides composition locals
    SettingsScaffold { // includes TopAppBar
        Box {
            Column {
                ThemePreferencesCategory() // subtitle
                ThemePreferenceItem() // menu item
            }
            themePrefs.preferenceHelper // shows when menu item clicked
        }
    }
}