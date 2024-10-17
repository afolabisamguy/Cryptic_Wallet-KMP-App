package components.notLoggedComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import presentation.screen.settings.SettingsScreen

@Composable
fun SettingsComp() {
    val navigator = LocalNavigator.currentOrThrow
    Row(
    modifier = Modifier
    .fillMaxWidth(),
    horizontalArrangement = Arrangement.Start
    ) {
        IconButton(onClick = { navigator.push(SettingsScreen()) }) {
            Icon(
                imageVector = Icons.Rounded.Settings,
                contentDescription = "Settings",
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}