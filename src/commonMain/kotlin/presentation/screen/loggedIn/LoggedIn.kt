package presentation.screen.loggedIn

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.loggedComponents.CryptoList
import components.loggedComponents.Heading
import components.loggedComponents.HeadingItem
import components.loggedComponents.LoggedComp1
import components.loggedComponents.TopLoggedBar
import components.loggedComponents.TopRow2
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Clock
import presentation.screen.ManageCrypto
import presentation.screen.settings.SettingsScreen
import viewModels.LoggedInViewModel
import viewModels.NotLoggedViewModel
import viewModels.ReceiveViewModel
import viewModels.RepoViewModel

class LoggedIn: Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val viewModel: ReceiveViewModel = getScreenModel()
        LaunchedEffect(Unit){
            viewModel.loadData()
            viewModel.updateToken()
        }
        val tokenList by viewModel.tokensList.collectAsState()

        val viewModel2: LoggedInViewModel = getScreenModel()
        val selectedHeading = viewModel2.selectedHeading

        val viewModel3 : RepoViewModel = getScreenModel()

        val navigator = LocalNavigator.currentOrThrow

        val scrollState = viewModel2.scrollState
        val showTopRow = viewModel2.showTopRow

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item { TopLoggedBar("Home") }
                item { LoggedComp1() }
                stickyHeader {
                    Spacer(modifier = Modifier.height(25.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            HeadingItem(
                                text = "Crypto",
                                isSelected = selectedHeading == Heading.HEADING1,
                                onClick = { viewModel2.weSetSelectedHeading(Heading.HEADING1) }
                            )
                            Spacer(modifier = Modifier.width(50.dp))
                            HeadingItem(
                                text = "NFTs",
                                isSelected = selectedHeading == Heading.HEADING2,
                                onClick = { viewModel2.weSetSelectedHeading(Heading.HEADING2) }
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    when (selectedHeading) {
                        Heading.HEADING1 -> {
                            tokenList.forEachIndexed { _, token ->
                                CryptoList(token, viewModel3)
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Box(modifier = Modifier
                                .clickable {
                                    navigator.push(ManageCrypto())
                                }
                                .fillMaxWidth()
                                .height(50.dp)
                                .align(Alignment.Center)
                            ) {
                                Text(
                                    text = "Manage Crypto",
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }

                        Heading.HEADING2 -> {
                            //NFTList()
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(500.dp))
                }
            }
                AnimatedVisibility(showTopRow,
                    enter = fadeIn() + slideInVertically() + expandVertically(),
                    exit = fadeOut() + slideOutVertically() + shrinkVertically()
                ){
                    TopRow2(modifier = Modifier.align(Alignment.TopCenter))
            }

        }
    }
}
