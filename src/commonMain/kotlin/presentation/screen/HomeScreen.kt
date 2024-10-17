package presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import presentation.screen.notLogged.NotLoggedScreen
import viewModels.NotLoggedViewModel
import viewModels.ReceiveViewModel
import viewModels.RepoViewModel

class HomeScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel: ReceiveViewModel = getScreenModel()
        val tokenList by viewModel.tokensList.collectAsState()
        val viewModel2 : RepoViewModel = getScreenModel()
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(bottom = 50.dp)
        ) {
            item {
                println("Entered HOmeScreen")
                //Implement debouncer for the buttons
                //When the button is clicked in fast succession the app crashes
                NotLoggedScreen(tokenList, viewModel2)
            }
        }
    }
}