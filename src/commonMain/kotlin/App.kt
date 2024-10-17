import Wallet.DriverFactory
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.softartdev.theme.material.PreferableMaterialTheme
import data.DatabaseHelper
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.mp.KoinPlatformTools
import presentation.tabs.DiscoverTab
import presentation.tabs.EarnTab
import presentation.tabs.HomeTab
import presentation.tabs.SwapTab
import repository.TokenFetch
import theme.MyCustomTheme
import viewModels.CreateWalletViewModel
import viewModels.EarnViewModel
import viewModels.LoggedInViewModel
import viewModels.NotLoggedViewModel
import viewModels.ReceiveViewModel
import viewModels.RepoViewModel


val lightPrimary = Color.Blue
val darkPrimary = Color(0xff2D60BC)



@Composable
@Preview
fun App() = PreferableMaterialTheme{
    initializeKoin()

    var isVisible by remember { mutableStateOf(true) }

    val homeTab = remember {
        HomeTab(
        onNavigator = { isVisible = it }
        )
    }

    val earnTab = remember {
        EarnTab(
            onNavigator = { isVisible = it }
        )
    }
    MyCustomTheme {
        TabNavigator(tab = homeTab){
            Scaffold(
                modifier = Modifier.fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                bottomBar = {
                    AnimatedVisibility(visible = isVisible,
                        enter = slideInVertically { height ->
                            height
                        },
                        exit = slideOutVertically { height ->
                            height
                        })
                    {
                        BottomNavigation {
                            TabNavigationItem(homeTab)
                            TabNavigationItem(SwapTab)
                            TabNavigationItem(earnTab)
                            TabNavigationItem(DiscoverTab)
                        }
                    }
                },
                content = { CurrentTab() }
            )
        }
    }
}


@Composable
private fun RowScope.TabNavigationItem(tab: Tab){
    val tabNavigator = LocalTabNavigator.current
    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        label = { Text(tab.options.title)},
        icon = { tab.options.icon?.let { Icon(painter = it, contentDescription = tab.options.title) } },
        selectedContentColor = Color.Blue,
        unselectedContentColor = Color.Black,
        modifier = Modifier.background(MaterialTheme.colors.background)

    )
}

object TabBarVisibilityState {
    var isVisible by mutableStateOf(true)

}

val walletModule = module {
    single { NotLoggedViewModel() }
    single { CreateWalletViewModel()}
    single { LoggedInViewModel() }
    single { ReceiveViewModel() }
    single { EarnViewModel() }
    single { RepoViewModel(get())}
    single { DatabaseHelper(get())}
    single { DriverFactory()}

}

fun initializeKoin() {
    if (KoinPlatformTools.defaultContext().getOrNull() == null) {
        startKoin {
            modules(walletModule)
        }
    }
}