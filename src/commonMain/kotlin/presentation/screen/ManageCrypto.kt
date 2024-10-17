package presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import components.CryptoList
import components.NetworkFilterDropdown
import components.SearchBar
import components.Topbar
import viewModels.ReceiveViewModel
import viewModels.RepoViewModel

class ManageCrypto: Screen {
    @Composable
    override fun Content() {
        val viewModel: ReceiveViewModel = getScreenModel()
        val viewModel2: RepoViewModel = getScreenModel()
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Topbar("Manage Crypto", true,)
            SearchBar()
            NetworkFilterDropdown()
            CryptoList(viewModel, viewModel2)
        }
    }
}