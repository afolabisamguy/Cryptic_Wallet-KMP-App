package presentation.screen.settings

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.mutableStateOf
import com.softartdev.theme.pref.ThemeEnum
import com.softartdev.theme.pref.ThemePrefs
import org.jetbrains.compose.resources.stringResource

object AppState {
    val showMaterial3: MutableState<Boolean> = mutableStateOf(false)
    val textState: MutableState<String> = mutableStateOf("Hello World")
    val scrollState: ScrollState = ScrollState(initial = 0)

    val switchMaterialCallback: () -> Unit = { showMaterial3.value = !showMaterial3.value }
    val changeMaterialCallback: (Boolean) -> Unit = { showMaterial3.value = it }

}

