package presentation.tabs

import Wallet.createDataStore
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import kotlinx.coroutines.launch
import presentation.screen.HomeScreen
import presentation.screen.loggedIn.LoggedIn
import repository.DataStoreRepo
import viewModels.CreateWalletViewModel
import kotlin.jvm.Transient
import kotlin.properties.Delegates


class HomeTab(
    @Transient
    val onNavigator: (isRoot: Boolean) -> Unit,
) : Tab {


    @Composable
    override fun Content() {
        val dataStoreRepository = remember {
            DataStoreRepo(dataStore = createDataStore())
        }
        val viewModel2: CreateWalletViewModel = getScreenModel()
        val scope = rememberCoroutineScope()
        val isLoggedIn by dataStoreRepository.isLoggedIn.collectAsState()
        println("isLoggedIn: $isLoggedIn")

        var isLogged = viewModel2.retrieveWallet()
        scope.launch {
            dataStoreRepository.updateLoginState(isLogged)
        }


        var screen: Screen = if (isLogged) {
            LoggedIn()
        } else {
            HomeScreen()
        }
        // Implement the coin screen
        // and all the other tabs

        Navigator(screen) { navigator ->
            LaunchedEffect(navigator.lastItem) {
                println("Navigator: ${navigator.lastItem}")
                onNavigator(navigator.lastItem is LoggedIn || navigator.lastItem is HomeScreen)
            }
            SlideTransition(navigator)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Rounded.Home)
            val title = "Home"
            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }
}