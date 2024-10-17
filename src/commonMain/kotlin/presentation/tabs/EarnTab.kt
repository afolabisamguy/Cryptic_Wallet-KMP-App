package presentation.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import presentation.screen.EarnScreen
import presentation.screen.SwapScreen
import kotlin.jvm.Transient


class EarnTab(
    @Transient
    val onNavigator: (isRoot: Boolean) -> Unit,
): Tab {
    @Composable
    override fun Content() {
        Navigator(EarnScreen()) { navigator ->
            LaunchedEffect(navigator.lastItem) {
                onNavigator(navigator.lastItem is EarnScreen)
            }
            SlideTransition(navigator)
        }
    }


    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Rounded.Call)
            val title = "Earn"
            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }
}